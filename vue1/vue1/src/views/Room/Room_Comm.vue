<template>
    <div class="user-all" align="center" style="background-color: lightsteelblue;height: 711px">
        <div class="box" id="gundong" align="center" style="width: 1000px;background-color: whitesmoke;border-radius: 4px;padding-left: 12px;
      padding-right: 12px;
      min-height: calc(100% - 160px);
      height: calc(100% - 140px);
      overflow-y: auto;" >


            <!--聊天表数据-->
            <div v-for="item2 in tableData2">

                <!--用户信息数据-->
                <div v-for="item in tableData" style="width: 95%">

                    <!--判断A用户，显示头像和姓名-->
                    <div v-if=" item2.contentb == ''" align="right">
                        <div v-if="item.account == item2.accounta">
                            <div>
                            <a target="_blank" style="color: mediumslateblue" data-clicklog="nick" class="name">{{item.name}}</a>
                            <el-avatar :src="item.turl"></el-avatar>
                            </div>

                            <!--显示聊天内容-->
                            <div style="margin-top: 20px;font-size: 17px">
                                <span>{{item2.contenta}}</span>
                                <el-divider></el-divider>
                            </div>
                        </div>
                        <!--<div style="height: 3px"></div>-->
                    </div>

                    <!--判断B用户，显示头像和姓名-->
                    <div v-if=" item2.contenta == ''" align="left">
                        <div v-if="item.account == item2.accountb">
                            <el-avatar :src="item.turl"></el-avatar>
                            <a target="_blank" style="color: mediumslateblue" data-clicklog="nick" class="name">{{item.name}}</a>

                            <!--显示聊天内容-->
                            <div style="margin-top: 20px;font-size: 17px">
                                <span>{{item2.contentb}}</span>
                                <el-divider></el-divider>
                            </div>
                        </div>
                        <!--<div style="height: 3px"></div>-->
                    </div>
                </div>
            </div>
        </div>



        <!--聊天框-->
        <div class="userpto" style="margin-left: 0%;width: 400px;margin-top: 30px">

            <el-form :model="ruleForm3" ref="ruleForm" class="demo-ruleForm">
                <tr>
                    <td>
                <el-form-item prop="text">

                    <el-input
                            style="width: 320px"
                                type="textarea"
                                autosize
                                placeholder="说点什么吧..."
                                v-model="ruleForm3.contenta">
                    </el-input>

                </el-form-item>
                    </td>
                    <td>
                <el-form-item style="margin-left:80%">
                    <el-button type="primary" @click="submitForm('ruleForm3')">发送</el-button>
                </el-form-item>
                    </td>
                </tr>
            </el-form>

        </div>

    </div>
</template>

<script>
    export default {
        inject: ['reload'],
        data() {
            return {

                account: '',
                name: '',
                accounta: '',
                tableData: [], //所有用户数据
                tableData2: [], //聊天列表数据
                ruleForm: [],
                // 聊天表数据
                ruleForm3: {
                    time: '',
                    contenta: '',
                    contentb: '',
                    accounta: window.sessionStorage.getItem('account'),
                    accountb: this.$route.query.account,
                },
                // 查询聊天用户
                ruleForm2: {
                    accounta: window.sessionStorage.getItem('account'),
                    accountb: this.$route.query.account,
                }


            }
        },

        methods: {
            gettime() {
                //获取日期
                const $date = new Date();
                const $time = $date.toLocaleString();
                this.ruleForm3.time = $time;
            },
            // 发送
            submitForm(formName) {
                // console.log(this.ruleForm3);
                this.gettime();
                const _this = this;


                axios.post('http://localhost:8181/room/save', this.ruleForm3).then(function (resp) {
                    if (resp.data == 'success') {
                        _this.$message({message: '已发送', type: 'success'});
                        _this.reload();
                    } else {
                        _this.$message.error('发送失败');
                    }
                });

                axios.post('http://localhost:8181/room/save2', this.ruleForm3).then(function (resp) {

                })

            },

            //获取所有用户数据
            getMember() {
                const _this = this;
                axios.post('http://localhost:8181/member/findAll2').then(function (resp) {
                    // console.log(resp);
                    _this.tableData = resp.data;
                })
            },
            //获取聊天内容
            getComm() {
                const _this = this;
                axios.post('http://localhost:8181/room/find', _this.ruleForm2).then(function (resp) {
                    // console.log(resp);
                    _this.tableData2 = resp.data;
                });
                // let info = {accounta: '聊天内容，触发后push一个'},
                //     _this.ruleForm3.accounta(info)
                // // 核心代码
                // // 滚动
                this.$nextTick(() => {
                    let msg = document.getElementById('gundong') // 获取对象
                    msg.scrollTop = msg.scrollHeight // 滚动高度
                });



                let i = 0;
                const run = setInterval(() => {
                    i++;
                    console.log(i);
                    if (i == 2) {
                        clearInterval(run)
                    } else {
                        axios.post('http://localhost:8181/room/find', _this.ruleForm2).then(function (resp) {
                            console.log(resp);
                            _this.tableData2 = resp.data;
                        })
                    }}, 2000);
            }

        },


        created() {
            const _this = this;
            axios.get('http://localhost:8181/member/findByAccount3/' + this.$route.query.account).then(function (resp) {
                // console.log(resp);
                //获取接收方用户数据
                _this.ruleForm = resp.data;
            });
            _this.getMember();
            _this.getComm();

        }
    }
</script>

<style scoped>
    .user-all {
        padding-top: 16px;
        margin-bottom: 16px;
        position: relative;
        word-wrap: break-word;
    }

</style>