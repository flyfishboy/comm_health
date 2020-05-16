<template>
    <el-form style="width: 60%" :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">

        <el-form-item label="姓名" prop="name">
            <el-input v-model="ruleForm.name"></el-input>
        </el-form-item>

        <el-form-item label="账号" prop="account">
            <el-input v-model="ruleForm.account"></el-input>
        </el-form-item>

        <el-form-item label="密码" prop="password">
            <el-input type="password" v-model="ruleForm.password"></el-input>
        </el-form-item>
        <el-form-item label="确认密码" prop="checkPass">
            <el-input type="password" v-model="ruleForm.checkPass" autocomplete="off" placeholder="请重新输入密码"></el-input>
        </el-form-item>

        <el-form-item label="性别" prop="sex">
            <el-radio v-model="ruleForm.sex" label="男">男</el-radio>
            <el-radio v-model="ruleForm.sex" label="女">女</el-radio>
        </el-form-item>

        <el-form-item label="电话号码" prop="tel">
            <el-input v-model="ruleForm.tel" placeholder="请输入所要添加人的电话号码"></el-input>
        </el-form-item>

        <el-form-item label="邮箱" prop="email">
            <el-input v-model="ruleForm.email"  placeholder="请输入邮箱/****@qq.com"></el-input>
        </el-form-item>

        <el-form-item label="健康情况" prop="health">
            <el-input v-model="ruleForm.health" placeholder="请输入您的健康情况"></el-input>
        </el-form-item>

        <el-form-item>
            <el-button type="primary" @click="submitForm('ruleForm')">修改</el-button>
            <el-button type="primary" @click="resetForm('ruleForm')">退出</el-button>
        </el-form-item>
    </el-form>
</template>


<script>
    export default {
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
                ruleForm: {
                    name: '',
                    account: '',
                    password:'',
                    sex:'',
                    tel:'',
                    email:'',
                    health:'',
                    checkPass:'',
                },
                rules: {
                    name: [
                        { required: true, message: '请输入姓名', trigger: 'blur' },
                        { min: 0, max: 20, message: '长度小于20 个字符', trigger: 'blur' }
                    ],
                    account: [
                        { required: true, message: '请输入账号', trigger: 'blur' },
                        { min: 0, max: 20, message: '长度小于20 个字符', trigger: 'blur' }
                    ],
                    // password: [
                    //     { required: true, message: '请输入密码', trigger: 'blur' },
                    //     { min: 0, max: 20, message: '长度小于20 个字符', trigger: 'blur' }
                    // ],
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
            submitForm(formName) {
                const _this = this;
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        axios.put('http://localhost:8181/member/update_a',this.ruleForm).then(function (resp) {
                            if(resp.data == 'success'){
                                _this.$alert(''+_this.ruleForm.name+'  修改成功！', '消息', {
                                    confirmButtonText: '确定' ,
                                    callback: action => {
                                        _this.$router.push('/Member_Person')
                                    }
                                })
                            }else{
                                _this.$message('修改失败');
                            }
                        })
                    } else {
                        return false;
                    }
                });
            },
            resetForm(formName) {
                // this.$refs[formName].resetFields();
                this.$router.push('/Member_Person')
            }
        },
        created() {
            const _this = this;
            axios.get('http://localhost:8181/member/findById/'+_this.$route.query.id).then(function (resp) {
                _this.ruleForm = resp.data
            })
        }
    }
</script>