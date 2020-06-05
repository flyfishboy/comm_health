<template>
    <div style="background-color: powderblue;height: 720px" align="center">
        <div style="margin-top: -1%">
            <h2>聊天用户</h2>
        </div>
        <el-input style="width: 20%" v-model="member.name" size="mini" placeholder="输入姓名搜索" @clear="getList()" clearable/>&nbsp;
        <el-button  @click="findName">搜索</el-button>
        <el-table
                :data="tableData"
                style="width: 100%;margin-top: 8px">
            <el-table-column
                    label="姓名"
                    prop="name">
            </el-table-column>
            <el-table-column
                    label="账号"
                    prop="account">
            </el-table-column>
            <el-table-column
                    label="性别"
                    prop="sex">
            </el-table-column>
            <el-table-column
                    label="健康情况"
                    prop="health">
            </el-table-column>

            <el-table-column
                    align="right"
                    label="操作">
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
                flag:'1',
                pageSize: 0,
                total: 0,
                tableData: [],
                tableData2: [],
                currentPage:1,
                member: {
                    name: ''
                },

            }
        },

        methods: {
            handleSizeChange(val) {
                console.log(`每页 ${val} 条`);
            },
            //获取第一页用户数据
            getList() {
                const _this = this;
                axios.get('http://localhost:8181/member/findAll/0/6').then(function (resp) {
                    // console.log(resp);
                    _this.tableData = resp.data.content;
                    _this.pageSize = resp.data.size;
                    _this.total = resp.data.totalElements;
                })
            },
            //获取用户所有数据
            getAll() {
                const _this = this;
                axios.get('http://localhost:8181/member/find_excel').then(function (resp) {
                    // console.log(resp);
                    _this.tableData2 = resp.data;

                })

            },

            // 模糊查找
            findName() {
                const _this = this;
                _this.flag = 2;
                axios.post('http://localhost:8181/member/findAllByName/0/6', this.member).then(function (resp) {
                    // console.log(resp);
                    _this.tableData = resp.data.content;
                    _this.pageSize = resp.data.size;
                    _this.total = resp.data.totalElements;
                })
            },
            //进入聊天室
            Edit_A(row) {
                this.$router.push({
                    path:'/Room_Comm',
                    query:{
                        id:row.id,
                        account:row.account,
                        name:row.name,
                    }
                })
            },

            //分页查出所有用户数据
            page(currentPage) {
                const _this = this;
                if(_this.flag == 1){
                axios.get('http://localhost:8181/member/findAll/' + (currentPage - 1) + '/6').then(function (resp) {
                    // console.log(resp);
                    _this.tableData = resp.data.content;
                    _this.pageSize = resp.data.size;
                    _this.total = resp.data.totalElements;
                })} else {
                axios.post('http://localhost:8181/member/findAllByName/' + (currentPage - 1) + '/6', this.member).then(function (resp) {
                    // console.log(resp);
                    _this.tableData = resp.data.content;
                    _this.pageSize = resp.data.size;
                    _this.total = resp.data.totalElements;
                })
                }
            }

        },

        created() {
            this.getList();
            this.getAll()
        }
    }
</script>