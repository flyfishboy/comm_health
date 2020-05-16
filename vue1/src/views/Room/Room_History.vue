<template>
    <div style="background-color: skyblue;height: 720px" align="center">
        <el-input style="width: 20%;" v-model="atob.nameb" size="mini" placeholder="输入姓名搜索" @clear="getList()" clearable/>&nbsp;
        <el-button  @click="findName">搜索</el-button>
        <el-table
                :data="tableData"
                style="width: 50%;margin-top: 8px">
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
        data() {
            return {
                pageSize: 0,
                total: 0,
                currentPage:1,
                tableData: [],
                track:{
                    accounta:window.sessionStorage.getItem("account"),
                },

               atob: {
                    nameb: ''
                },
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
                axios.post('http://localhost:8181/atob/findAllByName/0/6', this.atob).then(function (resp) {
                    // console.log(resp);
                    _this.tableData = resp.data.content;
                    _this.pageSize = resp.data.size;
                    _this.total = resp.data.totalElements;
                })
            },

            Edit_A(row) {   //进入聊天室

                this.$router.push({
                    path:'/Room_Comm',
                    query:{
                        id:row.id,
                        account:row.accountb,
                        name:row.nameb,
                    }
                })
            },

            page(currentPage) {
                const _this = this;
                axios.post('http://localhost:8181/atob/findAllByAccounta/' + (currentPage - 1) + '/6',this.track).then(function (resp) {
                    // console.log(resp);
                    _this.tableData = resp.data.content;
                    _this.pageSize = resp.data.size;
                    _this.total = resp.data.totalElements;
                })
            },

        },

        created() {
            this.getList();
        }
    }
</script>