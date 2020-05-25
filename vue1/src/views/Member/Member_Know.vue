<template>
    <div style="background-color: skyblue">
    <div align="center" >
    <!--推荐内容-->
        <el-card class="box-card" style="width: 60%" v-if="health != null">
            <div slot="header" class="clearfix">
                <span>推荐</span>
            </div>
            <div v-for="item in tableData2" class="text item" style="height: 10px">
                <div style="height: 10px">
                    <el-tag style="font-size: 18px">{{item.message}}</el-tag>&nbsp;
                    <el-tag type="success" style="font-size: 18px">{{item.health}}</el-tag>
                    <el-divider></el-divider>
                </div>

            </div>
        </el-card>

        <el-card class="box-card" style="width: 50%" v-else>
            <div slot="header" class="clearfix">
                <span>暂无</span>
            </div>

        </el-card>

    </div>
        <div style="height: 20px;"></div>
    <!--小常识展示-->
    <div style="height: 450px" align="center">
        <div style="margin-top: -1%">
            <!--<h2>聊天用户</h2>-->
        </div>
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
        data() {
            return {
                flag:'1',
                pageSize: 0,
                total: 0,
                tableData: [],
                tableData2: [],
                message:'',
                health:'',
                currentPage:1,
                health:window.sessionStorage.getItem("health"),
                message1:{
                  health:window.sessionStorage.getItem("health"),
                },
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

            getFind() {
                const _this = this;
                if(_this.health != null){
                axios.post('http://localhost:8181/message/find',this.message1).then(function (resp) {
                    console.log(resp);
                    _this.tableData2 = resp.data;
                })
                }else{
                    this.$message({
                        showClose: true,
                        message: '登录查看推荐信息'
                    });
                }
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
            this.getFind();//获取推荐的小常识数据
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