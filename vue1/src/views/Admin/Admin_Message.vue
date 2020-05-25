<template>
    <div >
        <!--小常识展示-->
        <div style="height: 450px" >
            <el-input style="width: 20%" v-model="message2.health" size="mini" placeholder="输入健康类别搜索" @clear="getList()" clearable/>&nbsp;
            <el-button  @click="findName">搜索</el-button>
            <el-table
                    :data="tableData"
                    style="width: 100%;margin-top: 8px">
                <el-table-column
                        label="小常识"
                        prop="message">
                </el-table-column>
                <el-table-column
                        label="健康类别"
                        prop="health">
                </el-table-column>

                <el-table-column
                        align="right"
                        label="操作" >
                    <template slot-scope="scope">
                        <el-button
                                size="mini"
                                type="danger"
                                @click="Delete_A(scope.row)">删除
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
                tableData: [],
                message:'',
                health:'',
                currentPage:1,
                message2: {
                    health: ''
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
                _this.flag = 1;
                axios.get('http://localhost:8181/message/findAll/0/6').then(function (resp) {
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
                axios.post('http://localhost:8181/message/findByHealthLike/0/6', this.message2).then(function (resp) {
                    // console.log(resp);
                    _this.tableData = resp.data.content;
                    _this.pageSize = resp.data.size;
                    _this.total = resp.data.totalElements;
                })
            },
            // 删除
            Delete_A(row) {
                const _this = this;
                axios.delete('http://localhost:8181/message/deleteById/'+row.id).then(function (resp) {
                    _this.$message({
                        message: '删除成功',
                        type: 'success'
                    });
                    _this.reload();
                })
            },

            //分页查出所有用户数据
            page(currentPage) {
                const _this = this;
                console.log(_this.flag);
                if(_this.flag == 1){
                    axios.get('http://localhost:8181/message/findAll/' + (currentPage - 1) + '/6').then(function (resp) {
                        // console.log(resp);
                        _this.tableData = resp.data.content;
                        _this.pageSize = resp.data.size;
                        _this.total = resp.data.totalElements;
                    })} else {
                    axios.post('http://localhost:8181/message/findByHealthLike/' + (currentPage - 1) + '/6', this.message2).then(function (resp) {
                        // console.log(resp);
                        _this.tableData = resp.data.content;
                        _this.pageSize = resp.data.size;
                        _this.total = resp.data.totalElements;
                    })
                }
            }

        },

        created() {
            this.getList();//获取所有小常识数据
        }
    }


</script>

<style scoped>
    .text {
        font-size: 14px;
    }

    .item {
        margin-bottom: 18px;
    }

    .clearfix:before,
    .clearfix:after {
        display: table;
        content: "";
    }
    .clearfix:after {
        clear: both
    }

    .box-card {
        width: 480px;
    }

</style>