<template>
    <div style="background-color: skyblue;height: 720px" align="center">
        <el-input style="width: 20%;" v-model="atob.namea" size="mini" placeholder="输入姓名搜索" @clear="getList2()" clearable/>&nbsp;
        <el-button  @click="findName">搜索</el-button>
        <el-table
                :data="tableData2"
                style="width: 50%;margin-top: 8px">
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
                <template slot-scope="scope">
                    <el-button
                            size="mini"
                            type="success"
                            @click="Edit_B(scope.row)">聊天
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
        data() {
            return {
                pageSize: 0,
                total: 0,
                currentPage:1,

                tableData2: [],
                track2:{
                    accountb:window.sessionStorage.getItem("account"),
                },
                atob: {
                    namea: ''
                },
            }
        },

        methods: {
            handleSizeChange(val) {
                console.log(`每页 ${val} 条`);
            },
            //获取收到信息的聊天记录（未回复）
            getList2() {
                const _this = this;
                axios.post('http://localhost:8181/atob/findAllByAccountb/0/6',this.track2).then(function (resp) {
                    // console.log(resp);
                    _this.tableData2 = resp.data.content;
                    _this.pageSize = resp.data.size;
                    _this.total = resp.data.totalElements;
                })
            },

            // 模糊查找
            findName() {
                const _this = this;
                axios.post('http://localhost:8181/atob/findAllByNamea/0/6', this.atob).then(function (resp) {
                    // console.log(resp);
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
                    }
                })
            },

            page(currentPage) {
                const _this = this;
                axios.post('http://localhost:8181/atob/findAllByAccountb/' + (currentPage - 1) + '/6',this.track2).then(function (resp) {
                    // console.log(resp);
                    _this.tableData2 = resp.data.content;
                    _this.pageSize = resp.data.size;
                    _this.total = resp.data.totalElements;
                })
            },

        },

        created() {
            this.getList2();
        }
    }
</script>