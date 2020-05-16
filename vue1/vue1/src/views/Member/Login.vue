<template>
    <div class="login-container">
        <div class="background">
            <img :src="imgSrc" width="100%" height="100%" alt="" />
        </div>

        <el-form :model="loginform" ref="loginform"  label-width="80px" class="login-form" :rules="rules">
            <h2 class="login-title" style="'center'">健康生活交流平台</h2>
            <el-form-item label="账号" class="box" prop="account">
                <el-input v-model="loginform.account" placeholder="请输入账号"></el-input>
            </el-form-item>
            <el-form-item label="密码" class="box" prop="password">
                <el-input v-model="loginform.password" type="password" placeholder="请输入密码"></el-input>
            </el-form-item>
            <el-form-item>
            <el-radio v-model="radio" value="user" label="1">会员</el-radio>
            <el-radio v-model="radio" value="admin" label="2">管理员</el-radio>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="onSubmit('loginform')">登录</el-button>
                <el-button type="primary" @click="register('loginform')">注册</el-button>
                <el-button type="primary" @click="visit">游客登录</el-button>
            </el-form-item>
        </el-form>
    </div>


</template>


<script>
    import vueCanvasNest from "vue-canvas-nest";

    export default {
        data() {
            return {
                radio: '1',
                imgSrc:require('../../assets/login.jpg'),
                loginform: {
                    name:"",
                    account:"",
                    password: ""
                },
                rules: {
                    account: [
                        { required: true, message: '请输入账号', trigger: 'blur' },
                        { min: 0, max: 20, message: '长度小于20 个字符', trigger: 'blur' }
                    ],
                    password: [
                        { required: true, message: '请输入密码', trigger: 'blur' },
                        { min: 0, max: 20, message: '长度小于20 个字符', trigger: 'blur' }
                    ]
                }
            };
        },
        components: {
            vueCanvasNest
        },

        methods: {
            visit(){
              this.$router.push("/Home")
            },

            register(form) { //注册
                this.$router.push("/Register");
            },
            onSubmit(form) { //登录
                const _this = this;
                if(this.radio ==1) { //会员
                    this.$refs[form].validate((valid) => {
                        if (valid) {
                            axios.post('http://localhost:8181/member/login', this.loginform).then(function (resp) {
                                console.log(resp);

                                if (resp.data.code == 200) {
                                    window.sessionStorage.setItem('id',resp.data.data[0].id);
                                    window.sessionStorage.setItem('name',resp.data.data[0].name);
                                    window.sessionStorage.setItem('account',resp.data.data[0].account);
                                    window.sessionStorage.setItem('password',resp.data.data[0].password);
                                    window.sessionStorage.setItem('sex',resp.data.data[0].sex);
                                    window.sessionStorage.setItem('tel',resp.data.data[0].tel);
                                    window.sessionStorage.setItem('email',resp.data.data[0].email);
                                    window.sessionStorage.setItem('health',resp.data.data[0].health);
                                    window.sessionStorage.setItem('turl',resp.data.data[0].turl);
                                    _this.$message({showClose:true,message: '  登录成功', type: 'success'});
                                     _this.$router.push('/Home')

                                } else {
                                    _this.$message.error('账号或密码错误');
                                }
                            })
                        } else {
                            return false;
                        }
                    });
                }else { //管理员
                    this.$refs[form].validate((valid) => {
                        if (valid) {
                            axios.post('http://localhost:8181/admin/login', this.loginform).then(function (resp) {
                                console.log(resp);

                                if (resp.data.code == 200) {
                                    window.sessionStorage.setItem('id',resp.data.data[0].id);
                                    window.sessionStorage.setItem('name',resp.data.data[0].name);
                                    window.sessionStorage.setItem('account',resp.data.data[0].account);
                                    window.sessionStorage.setItem('password',resp.data.data[0].password);
                                    window.sessionStorage.setItem('tel',resp.data.data[0].tel);
                                    _this.$message({showClose: true,message: '登录成功', type: 'success'});
                                    _this.$router.push({
                                        path:'/Admin_Person',
                                        query:{
                                            id:resp.data.data[0].id,
                                        }
                                    })
                                } else {
                                    _this.$message.error('账号或密码错误');
                                }
                            })
                        } else {
                            return false;
                        }
                    });
                }
            }
        }
    }
</script>


<style scoped>
    .login-form {
        width: 350px;
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
        height:100%;  /**宽高100%是为了图片铺满屏幕 */
        z-index:-1;
        position: absolute;
    }

    .login-container {
        position: absolute;
        width: 100%;
        height: 100%;
        z-index:1;
    }

    .login-title {
        color: #303133;
        text-align: center;
    }

    .cancle {
        margin-left: 80px;
    }

    .box {
        margin-left: 0px;
    }
</style>