<template>
    <el-form style="width: 60%" :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">

        <el-form-item label="健康小常识" prop="message">
            <el-input v-model="ruleForm.message" placeholder="请输入所要添加的内容"></el-input>
        </el-form-item>

        <el-form-item label="健康类别" prop="health">
            <el-input v-model="ruleForm.health" placeholder="请输入所要添加健康类别"></el-input>
        </el-form-item>

        <el-form-item>
            <el-button type="primary" @click="submitForm(ruleForm)">提交</el-button>
            <el-button type="primary" @click="resetForm('ruleForm')">重置</el-button>

        </el-form-item>
    </el-form>
</template>
<script>
    export default {
        inject:['reload'],
        data() {
            return {
                ruleForm: {
                    message: '',
                    health: '',
                },

                rules: {
                    message: [
                        { required: true, message: '请输入内容', trigger: 'blur' },
                        { min: 5, max: 40, message: '长度小于40 个字符', trigger: 'blur' }
                    ],
                    account: [
                        { required: true, message: '请输入健康类别', trigger: 'blur' },
                        { min: 1, max: 20, message: '长度小于20 个字符', trigger: 'blur' }
                    ],
                }
            };
        },
        methods: {
            submitForm(item) {
                const _this = this;
                   if(item.message != '' && item.health != ''){
                        axios.post('http://localhost:8181/message/save',item).then(function (resp) {
                            if(resp.data == 'success'){
                                _this.$alert(' 添加成功！', '消息', {
                                    confirmButtonText: '确定',
                                    callback: action => {
                                        _this.$router.push('/Admin_Message')
                                    }
                                })
                            }else{
                                _this.$message('添加失败');
                            }
                        })
                    } else {
                        _this.$message({message: '请将内容填写完整', type: 'error'});
                    }
            },

            resetForm(formName) {
                this.$refs[formName].resetFields();
            }
        }
    }
</script>