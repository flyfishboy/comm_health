<template>
<el-form style="width: 60%" :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">

    <el-form-item label="姓名" prop="name">
        <el-input v-model="ruleForm.name" placeholder="请输入所要添加人的姓名"></el-input>
    </el-form-item>

    <el-form-item label="账号" prop="account">
        <el-input v-model="ruleForm.account" placeholder="请输入所要添加人的账号"></el-input>
    </el-form-item>

    <el-form-item label="密码" prop="password">
        <el-input v-model="ruleForm.password" placeholder="请输入所要添加人的密码"></el-input>
    </el-form-item>

    <el-form-item label="性别" prop="sex">
        <el-radio v-model="ruleForm.sex" label="男">男</el-radio>
        <el-radio v-model="ruleForm.sex" label="女">女</el-radio>
    </el-form-item>
    <el-form-item label="电话号码" prop="tel">
        <el-input v-model="ruleForm.tel" placeholder="请输入所要添加人的电话号码"></el-input>
    </el-form-item>

    <el-form-item label="健康情况" prop="health">
        <el-input v-model="ruleForm.health" placeholder="请输入所要添加人的健康情况"></el-input>
    </el-form-item>


    <el-form-item>
        <el-button type="primary" @click="submitForm('ruleForm')">提交</el-button>
        <el-button type="primary" @click="resetForm('ruleForm')">重置</el-button>

    </el-form-item>
</el-form>
</template>
<script>
    export default {
        data() {
            return {
                ruleForm: {
                    name: '',
                    account: '',
                    password:'',
                    sex:'',
                    tel:'',
                    health:''
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
                    password: [
                        { required: true, message: '请输入密码', trigger: 'blur' },
                        { min: 0, max: 20, message: '长度小于20 个字符', trigger: 'blur' }
                    ],
                    tel: [
                        { required: true, message: '请输入有效电话号码', trigger: 'blur' },
                        { min: 7, max: 11, message: '7-11位数电话号码', trigger: 'blur' }
                    ],
                    health: [
                        { required: true, message: '请输入您的健康情况', trigger: 'blur' },
                        { min: 0, max: 20, message: '长度小于20 个字符', trigger: 'blur' }
                    ]
                }
            };
        },
        methods: {
            submitForm(formName) {
                const _this = this;
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        axios.post('http://localhost:8181/member/save',this.ruleForm).then(function (resp) {
                            if(resp.data == 'success'){
                                _this.$alert(''+_this.ruleForm.name+'  添加成功！', '消息', {
                                    confirmButtonText: '确定',
                                    callback: action => {
                                        _this.$router.push('/Admin_Manage')
                                    }
                                })
                            }else{
                                _this.$message('添加失败');
                            }
                        })
                    } else {
                        return false;
                    }
                });
            },

            resetForm(formName) {
                this.$refs[formName].resetFields();
            }
        }
    }
</script>