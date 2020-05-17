<template>
    <div style="background-color: skyblue;height: 710px" align="center">
        <div style="margin-top: -1%">
            <h2>聊天历史记录</h2>
        </div>
        <el-input style="width: 20%;" v-model="atob.nameb" size="mini" placeholder="输入姓名搜索" @clear="getList()" clearable/>&nbsp;
        <el-button  @click="findName">搜索</el-button>
        <el-table
                :data="tableData"
                style="width: 50%;margin-top: 8px">
            <el-table-column
                    label="最后发送时间"
                    prop="time">
            </el-table-column>
            <el-table-column
                    label="姓名"
                    prop="nameb">
            </el-table-column>
            <el-table-column
                    label="账号"
                    prop="accountb">
            </el-table-column>

            <el-table-column
                    align="right">
                <template slot-scope="scope">
                    <el-button
                            size="mini"
                            type="warning"
                            @click="Dele_A(scope.row)">删除
                    </el-button>
                    <el-button
                            size="mini"
                            type="success"
                            @click="Edit_A(scope.row)">聊天
                    </el-button>
                </template>
            </el-table-column>

        </el-table>

        <div class="block" align="center">
            <el-pagination
                    @size-change="handleSizeChange"
                    @current-change="page"
                    :current-page.sync="currentPage"
                    :page-size="pageSize"
                    layout="prev, pager, next, jumper"
                    :total="total">
            </el-pagination>
        </div>

    </div>
</template>

<script>
    export default {
        inject:['reload'],
        data() {
            return {
                flag:'1',
                pageSize: 0,
                total: 0,
                currentPage:1,
                tableData: [],
                //查询我发给对象消息的信息

                track:{
                    accounta:window.sessionStorage.getItem("account"),
                },
                //存储好友
                saveatob:{
                    id:'',
                    accounta:window.sessionStorage.getItem("account"),
                    accountb:'',
                    namea:window.sessionStorage.getItem("name"),
                    nameb:'',
                    badge:true,
                },

               atob: {
                    nameb: '',
                    accounta:window.sessionStorage.getItem("account"),
                },
                //删除用户发给对象的信息
                delete1:{
                    accounta:window.sessionStorage.getItem("account"),
                    accountb:'',
                },
                //删除对象发给用户的信息
                delete2:{
                    accounta:'',
                    accountb:window.sessionStorage.getItem("account"),
                }
            }
        },

        methods: {
            handleSizeChange(val) {
                console.log(`每页 ${val} 条`);
            },
            //获取当前用户的聊天记录（登录用户所面对的用户）
            getList() {
                const _this = this;
                axios.post('http://localhost:8181/atob/findAllByAccounta/0/6',this.track).then(function (resp) {
                    // console.log(resp);
                    _this.tableData = resp.data.content;
                    _this.pageSize = resp.data.size;
                    _this.total = resp.data.totalElements;
                })
            },

            // 模糊查找
            findName() {
                const _this = this;
                _this.flag = 2;
                axios.post('http://localhost:8181/atob/findAllByName2/0/6', this.atob).then(function (resp) {
                    // console.log(resp);
                    _this.tableData = resp.data.content;
                    _this.pageSize = resp.data.size;
                    _this.total = resp.data.totalElements;
                });

            },

            Edit_A(row) {   //进入聊天室
                this.$router.push({
                    path:'/Room_Comm',
                    query:{
                        id:row.id,
                        account:row.accountb,
                        name:row.nameb,
                    }
                });
                this.saveatob.id = row.id;
                this.saveatob.nameb = row.nameb;
                this.saveatob.accountb = row.accountb;
                //保存到atob表，存储两个聊天人的数据
                axios.post('http://localhost:8181/atob/saveBadge',this.saveatob ).then(function (resp) {
                    console.log(resp);
                });
            },

            Dele_A(row){
                this.delete1.accountb = row.accountb;
                this.delete2.accounta = row.accountb;

                const _this = this;
                //删除两个人的聊天记录
                axios.post('http://localhost:8181/room/deleteByAB',this.delete1).then(function (resp) {
                });
                axios.post('http://localhost:8181/room/deleteByAB',this.delete2).then(function (resp) {
                });
                //删除两个人的好友记录
                axios.post('http://localhost:8181/atob/deleteByAB',this.delete2).then(function (resp) {
                });
                axios.post('http://localhost:8181/atob/deleteByAB',this.delete1).then(function (resp) {
                    _this.$alert('  已删除记录！', '消息', {
                        confirmButtonText: '确定',
                        callback: action => {
                            _this.reload();
                        }
                    })
                })
            },

            page(currentPage) {
                const _this = this;
                if(_this.flag == 1){
                axios.post('http://localhost:8181/atob/findAllByAccounta/' + (currentPage - 1) + '/6',this.track).then(function (resp) {
                    // console.log(resp);
                    _this.tableData = resp.data.content;
                    _this.pageSize = resp.data.size;
                    _this.total = resp.data.totalElements;
                });}else {
                axios.post('http://localhost:8181/atob/findAllByName2/' + (currentPage - 1) + '/6',this.atob).then(function (resp) {
                    // console.log(resp);
                    _this.tableData = resp.data.content;
                    _this.pageSize = resp.data.size;
                    _this.total = resp.data.totalElements;
                })
                }
            },

        },

        created() {
            this.getList();
        }
    }
</script>