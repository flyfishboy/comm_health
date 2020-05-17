<template>
    <div style="background-color: skyblue;height: 710px" align="center">
        <div style="margin-top: -1%">
            <h2>消息提示</h2>
        </div>
        <el-input style="width: 20%;" v-model="atob.namea" size="mini" placeholder="输入姓名搜索" @clear="getList2()" clearable/>&nbsp;
        <el-button  @click="findName">搜索</el-button>

        <div v-for="item1 in tableData2">
            <div v-for="item2 in tableData3">
                <div v-if="item1.accounta != item2.accountb">

                    <span>{{item1.accounta}}</span>
                </div>
            </div>
        </div>

        <el-table
                :data="tableData2"
                style="width: 50%;margin-top: 8px">
            <el-table-column
                    label="最后发送时间"
                    prop="time">
            </el-table-column>
            <el-table-column
                    label="姓名"
                    prop="namea">
            </el-table-column>
            <el-table-column
                    label="账号"
                    prop="accounta">
            </el-table-column>

            <el-table-column

                    align="right">
                <template slot-scope="scope" >
                    <el-button
                            size="mini"
                            type="warning"
                            @click="Dele_A(scope.row)">删除
                    </el-button>
                    <el-badge  is-dot>
                    <el-button
                            size="mini"
                            type="success"
                            @click="Edit_B(scope.row)">聊天
                    </el-button>
                    </el-badge>
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
                a:true,
                flag:'1',
                pageSize: 0,
                total: 0,
                currentPage:1,
                tableData2: [],
                tableData3: [],

                //保存标识符
                badge2:{
                    id:'',
                    accounta:'',
                    accountb:window.sessionStorage.getItem("account"),
                    namea:'',
                    nameb:window.sessionStorage.getItem("name"),
                    badge:false,
                },

                //查询发来消息的人
                track2:{
                    accountb:window.sessionStorage.getItem("account"),
                    badge:true,
                },

                atob: {
                    namea: '',
                    accountb:window.sessionStorage.getItem("account"),
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
            //获取收到信息的聊天记录（未回复）
            getList2() {
                const _this = this;
                axios.post('http://localhost:8181/atob/findAllByBadge/0/6',this.track2).then(function (resp) {
                    console.log(resp);
                    _this.tableData2 = resp.data.content;
                    _this.pageSize = resp.data.size;
                    _this.total = resp.data.totalElements;

                })
            },

            Dele_A(row){
                this.delete1.accountb = row.accounta;
                this.delete2.accounta = row.accounta;

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
                    _this.$alert('  已删除！', '消息', {
                        confirmButtonText: '确定',
                        callback: action => {
                            _this.reload();
                        }
                    })
                })
            },

            // 模糊查找
            findName() {
                const _this = this;
                _this.flag = 2;
                axios.post('http://localhost:8181/atob/findAllByNamea/0/6', this.atob).then(function (resp) {
                    console.log(resp);
                    _this.tableData2 = resp.data.content;
                    _this.pageSize = resp.data.size;
                    _this.total = resp.data.totalElements;
                })
            },

            //进入聊天室
            Edit_B(row) {

                this.$router.push({
                    path:'/Room_Comm',
                    query:{
                        id:row.id,
                        account:row.accounta,   //将发消息人的account传到聊天室页面
                        name:row.namea,
                    }
                });

                this.badge2.id = row.id;
                this.badge2.namea = row.namea;
                this.badge2.accounta = row.accounta;
                //保存到atob表，存储两个聊天人的数据
                axios.post('http://localhost:8181/atob/saveBadge',this.badge2 ).then(function (resp) {
                    console.log(resp);
                });

            },

            page(currentPage) {
                const _this = this;
                if(_this.flag == 1){
                axios.post('http://localhost:8181/atob/findAllByBadge/' + (currentPage - 1) + '/6',this.track2).then(function (resp) {
                    console.log(resp);
                    _this.tableData2 = resp.data.content;
                    _this.pageSize = resp.data.size;
                    _this.total = resp.data.totalElements;

                });}else {
                    axios.post('http://localhost:8181/atob/findAllByNamea/' + (currentPage - 1) + '/6', this.atob).then(function (resp) {
                        // console.log(resp);
                        _this.tableData2 = resp.data.content;
                        _this.pageSize = resp.data.size;
                        _this.total = resp.data.totalElements;
                    })
                }
            },

        },

        created() {
            this.getList2();
            // this.getList1();
        }
    }
</script>