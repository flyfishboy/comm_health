<template>
    <div >
        <div class="background" style="margin-top: -25px" >
        <img style="height:80%;width:99.5%" src="../../assets/mail3.jpg">
        </div>
        <div style="height: 40px"></div>
    <div align="center">
        <div>
            <h1>制定你的生活计划</h1>
        </div>
        <div style="height: 20px"></div>
        <div>
            <h5>你的邮箱：{{email}}</h5>
        </div>
        <div>
    <el-form style="width: 60%" :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">

        <el-form-item label="标题" prop="title">
            <el-input v-model="ruleForm.title"  placeholder="请输入标题"></el-input>
        </el-form-item>

        <el-form-item label="内容" prop="text">
            <el-input type="textarea" autosize v-model="ruleForm.text"  placeholder="请输入内容"></el-input>
        </el-form-item>

        <el-form-item>
            <el-button type="primary" plain @click="submit('ruleForm')">提交</el-button>
        </el-form-item>

    </el-form>
        </div>
    </div>
    </div>
</template>

<script>

    export default {
        inject:['reload'],                                 //注入App里的reload方法
        data() {
            return {
                email:window.sessionStorage.getItem("email"),
                ruleForm: {
                    email:window.sessionStorage.getItem("email"),
                    title:'',
                    text: '',
                    account:sessionStorage.getItem('account'),
                    time:'',
                },
                rules: {
                    title: [
                        {required: true, message: '请输入邮件标题', trigger: 'blur'},
                        {min: 0, max: 20, message: '长度小于20 个字符', trigger: 'blur'}
                    ],
                    text: [
                        {required: true, message: '请输入邮件内容', trigger: 'blur'},
                        {min: 0, max: 200, message: '长度小于200个字符', trigger: 'blur'}
                    ],
                }
            }
        },
        methods: {
            submit(form) {
                //获取日期
                const $date = new Date();
                const $time = $date.toLocaleString();
                this.$notify({
                    title: '提示',
                    message: '正在发送邮件，请勿跳转页面，等待确认',
                    offset: 100
                });
                const _this = this;
                axios.post('http://localhost:8181/qqemail', this.ruleForm).then(function (resp) {
                    if (resp.data == '发送成功') {
                        _this.$alert('发送成功！', '消息', {
                            confirmButtonText: '确定',
                            callback: action => {
                                _this.reload();
                            }
                        });

                    } else {
                        console.log(resp.data);
                        _this.$message('发送失败');
                    }
                });
                // 将数据保存
                this.ruleForm.time = $time;
                axios.post('http://localhost:8181/email/save', this.ruleForm).then(function (resp) {
                    if (resp.data == 'success') {
                        // window.sessionStorage.setItem('time', _this.ruleForm.time);
                        // _this.$message({message: '已将数据保存', type: 'success'});

                    } else {
                        _this.$message('请重试');
                    }
                });
                // 定时发送
                let i = 0;
                const run = setInterval(() => {
                    const _this = this;
                    i++;
                    console.log(i);
                    if (i == 3) {
                        clearInterval(run)
                    } else {
                        axios.post('http://localhost:8181/qqemail', this.ruleForm).then(function (resp) {
                            if (resp.data == '发送成功') {
                                _this.$message({
                                    showClose: true,
                                    message: '健康生活计划，已发送提醒',
                                    type: 'success'
                                });
                            } else {
                                console.log(resp.data);
                                _this.$message('提醒失败');
                            }
                        })
                    }
                }, 15000);
            },

        },

    };
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
    .background{
        /*width:100%;*/
        /*height:100%;  !**宽高100%是为了图片铺满屏幕 *!*/
        z-index:-1;
        position: absolute;
    }
</style>

