<template>
    <div class="register-container">
        <div class="background">
            <img :src="imgSrc" width="100%" height="100%" alt="" />
        </div>
        <el-form  style="width: 30% ;height: 70%" :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="register-form">
            <h2 class="login-title" style="'center'">健康生活交流平台</h2>

            <el-form-item label="姓名" prop="name">
                <el-input v-model="ruleForm.name" placeholder="请输入的姓名"></el-input>
            </el-form-item>

            <el-form-item label="账号" prop="account">
                <el-input v-model="ruleForm.account" placeholder="请输入账号"></el-input>
            </el-form-item>

            <el-form-item label="密码" prop="password">
                <el-input type="password" v-model="ruleForm.password" autocomplete="off" placeholder="请输入密码"></el-input>
            </el-form-item>

            <el-form-item label="确认密码" prop="checkPass">
                <el-input type="password" v-model="ruleForm.checkPass" autocomplete="off" placeholder="请重新输入密码"></el-input>
            </el-form-item>

            <el-form-item label="性别" prop="sex">
                <el-radio v-model="ruleForm.sex" label="男">男</el-radio>
                <el-radio v-model="ruleForm.sex" label="女">女</el-radio>
            </el-form-item>
            <el-form-item label="电话号码" prop="tel">
                <el-input v-model="ruleForm.tel" placeholder="请输入电话号码"></el-input>
            </el-form-item>

            <el-form-item label="邮箱" prop="email">
                <el-input v-model="ruleForm.email"  placeholder="请输入邮箱/****@qq.com"></el-input>
            </el-form-item>

            <el-form-item label="健康情况" prop="health">
                <el-input v-model="ruleForm.health" placeholder="请输入您的健康情况"></el-input>
            </el-form-item>

            <el-form-item style="margin-left: 10%">
                <el-button type="primary" @click="submitForm(ruleForm)">提交</el-button>
                <el-button type="primary" @click="resetForm('ruleForm')">重置</el-button>

            </el-form-item>

        </el-form>
    </div>
</template>
<script>
    export default {
        inject:['reload'],
        data() {
            var validatePass = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('请输入密码'));
                } else {
                    if (this.ruleForm.checkPass !== '') {
                        this.$refs.ruleForm.validateField('checkPass');
                    }
                    callback();
                }
            };
            var validatePass2 = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('请再次输入密码'));
                } else if (value !== this.ruleForm.password) {
                    callback(new Error('两次输入密码不一致!'));
                } else {
                    callback();
                }
            };
            return {
                imgSrc:require('../../assets/register2.jpg'),
                ruleForm: {
                    name: '',
                    account: '',
                    password:'',
                    sex:'',
                    tel:'',
                    email:'',
                    health:'',
                    checkPass:''
                },
                tableData:[],

                rules: {
                    name: [
                        { required: true, message: '请输入姓名', trigger: 'blur' },
                        { min: 0, max: 20, message: '长度小于20 个字符', trigger: 'blur' }
                    ],
                    account: [
                        { required: true, message: '请输入账号', trigger: 'blur' },
                        { min: 5, max: 20, message: '长度大于5字符小于20 个字符', trigger: 'blur' }
                    ],
                    tel: [
                        { required: true, message: '请输入有效电话号码', trigger: 'blur' },
                        { min: 7, max: 11, message: '7-11位数电话号码', trigger: 'blur' }
                    ],
                    email: [
                        { required: true, message: '请输入邮箱地址', trigger: 'blur' },
                        { type: 'email', message: '请输入正确的邮箱地址', trigger: ['blur', 'change'] }
                    ],
                    health: [
                        { required: true, message: '请输入您的健康情况', trigger: 'blur' },
                        { min: 0, max: 20, message: '长度小于20 个字符', trigger: 'blur' }
                    ],
                    password: [
                        { validator: validatePass, trigger: 'blur' }
                    ],
                    checkPass: [
                        { validator: validatePass2, trigger: 'blur' }
                    ],

                }
            };
        },
        methods: {
            submitForm(item) {
                const _this = this;
                        axios.post('http://localhost:8181/member/findAllByAccount2/' + item.account).then(function (resp1) {
                            console.log(resp1.data);
                            if(resp1.data == 'success'){
                                _this.$message({message: '账号已被注册，请重新注册', type: 'error'});
                                _this.reload()
                                   } else if(item.name != '' && item.account != '' && item.password != '' && item.sex != '' && item.tel != '' && item.email != '' && item.health != '' && item.checkPass != '' ){
                                axios.post('http://localhost:8181/member/save',item).then(function (resp) {
                                    if(resp.data == 'success'){
                                        _this.$alert(''+_this.ruleForm.name+'  注册成功！', '消息', {
                                            confirmButtonText: '确定',
                                            callback: action => {
                                                _this.$router.push('/')
                                            }
                                        })
                                    }else{
                                        _this.$message('注册失败');
                                    }
                                })
                           } else {
                                _this.$message({message: '请将信息填写完整', type: 'error'});
                            }
                        });
            },

            resetForm(formName) {
                this.$refs[formName].resetFields();
            }
        }
    }
</script>

<style scoped>
    .register-form {
        width: 200px;
        /* 上下嫌隙 160px，左右自动居中 */
        margin: 160px auto;
        background-color: rgb(255, 255, 255);
        padding: 28px;
        border-radius: 20px;
        /* border: solid 1px black; */
        box-shadow: 0 0 30px 3px rgba(119,118,118,0.33);
    }

    .background{
        width:100%;
        height:120%;  /**宽高100%是为了图片铺满屏幕 */
        z-index:-1;
        position: absolute;
    }

    .register-container {
        position: absolute;
        width: 100%;
        height: 120%;
        z-index:1;
    }

    .login-title {
        color: #303133;
        text-align: center;
    }

</style>
