<template >
    <div  class="user-all" align="center" style="background-color: powderblue;height: 680px" >

        <el-form v-for="item in tableData" style="width: 60%" align="left" >

            <div class="userpto" style="margin-left: 25%;"   >
                <div >
                <!--上传头像-->
                <el-popover
                        placement="right"
                        width="400"
                        trigger="click">
                    <div align="center">
                        <el-form :model="ruleForm" ref="ruleForm" label-width="80px" >
                            <el-upload
                                    prop="turl"
                                    class="upload-demo"
                                    drag
                                    ref="upload"
                                    action="http://localhost:8181/upload"
                                    accept="image/png,image/gif,image/jpg,image/jpeg"
                                    :before-upload="handleBeforeUpload"
                                    :on-preview="handlePreview"
                                    :on-remove="handleRemove"
                                    :on-success="handleAvatarSuccess"
                                    name="myFile"
                                    list-type="picture"
                                    v-model="ruleForm.turl"
                                    multiple>
                                <i class="el-icon-upload"></i>
                                <div class="el-upload__text">将图片拖到此处，或<em>点击上传</em></div>
                                <div class="el-upload__tip" slot="tip">只能上传jpg/png文件，且不超过2Mb</div>
                            </el-upload>
                            <el-form-item style="margin-left: -20%">
                                <el-button type="primary" @click="onSubmit('ruleForm')">上传</el-button>
                            </el-form-item>
                        </el-form>
                    </div>
                    <el-button style="background-color:powderblue" slot="reference">  <el-avatar :src="item.turl"></el-avatar></el-button>
                </el-popover>
                </div>

                <div style="height: 30px"></div>
                <div style="margin-left: 90px">
                 <span style="font-size: 23px;font-weight: bold;"> 姓名：</span>
                  <a target="_blank" style="font-size: 18px"  data-clicklog="nick" class="name">{{item.name}}</a>
                    <div style="height:  20px"></div>
                    <span style="font-size: 23px;font-weight: bold;"> 账户：</span>
                   <span style="font-size: 18px" >{{item.account}}</span>
                    <div style="height:  20px"></div>
                    <span style="font-size: 23px;font-weight: bold;"> 性别：</span>
                    <span style="font-size: 18px" >{{item.sex}}</span>
                    <div style="height:  20px"></div>
                    <span style="font-size: 23px;font-weight: bold;"> 电话：</span>
                    <span style="font-size: 18px" >{{item.tel}}</span>
                    <div style="height:  20px"></div>
                    <span style="font-size: 23px;font-weight: bold;"> 邮箱：</span>
                    <span style="font-size: 18px" >{{item.email}}</span>
                    <div style="height:  20px"></div>
                    <span style="font-size: 23px;font-weight: bold;"> 健康情况：</span>
                    <span style="font-size: 18px" >{{item.health}}</span>

                <div style="height: 30px"></div>
                <div style="margin-left: 50px">
                    <el-button type="primary"  @click="UpdateP(item.id)">修改</el-button>
                </div>
                </div>
            </div>

        </el-form>
    </div>
</template>


<script>

    export default {
        inject:['reload'],                                 //注入App里的reload方法
        data() {
            return {
                ruleForm:{
                    id:window.sessionStorage.getItem("id"),
                    name: window.sessionStorage.getItem('name'),
                    account: window.sessionStorage.getItem('account'),
                    password:window.sessionStorage.getItem('password'),
                    sex:window.sessionStorage.getItem('sex'),
                    tel:window.sessionStorage.getItem('tel'),
                    email:window.sessionStorage.getItem('email'),
                    health:window.sessionStorage.getItem('health'),
                    turl:'',
                },
                tableData:[],
                email:'',

            };
        },
        created(){
            const _this = this;
            axios.post("http://localhost:8181/member/findAllByAccount/"+window.sessionStorage.getItem('account')).then(function (resp) {
                // console.log(resp);
                _this.tableData = resp.data;
                window.sessionStorage.setItem("turl",resp.data[0].turl);
            })
        },

        methods: {
            UpdateP(item) {   //修改方法
                this.$router.push({
                    path:'/Member_Update',
                    query:{
                        id:item,
                    }
                })
            },

            //上传头像
            onSubmit(formName) {
                const _this = this;
                axios.put('http://localhost:8181/member/update_a', this.ruleForm).then(function (resp) {
                    if (resp.data == 'success') {
                        _this.$alert(' 上传头像成功！', '消息', {
                            confirmButtonText: '确定',
                            callback: action => {
                                _this.reload();
                            }
                        });

                    } else {
                        _this.$message('上传失败');
                    }
                })

            },

            //本地上传文件
            handleBeforeUpload(file) {
                const isLt2M = file.size / 1024 / 1024 < 2;
                if (!isLt2M) {
                    this.$message.error('上传文件大小不能超过 2MB!')
                }
                return isLt2M
            },
            handleBeforeUpload2(file) {
                const isLt2M = file.size / 1024 / 1024 < 20;
                if (!isLt2M) {
                    this.$message.error('上传文件大小不能超过 20MB!')
                }
                return isLt2M
            },
            handleRemove(file, fileList) {
                console.log(file, fileList);
            },
            handlePreview(file) {
                console.log(file);
            },

            handleAvatarSuccess(response) { //上传图片成功的函数
                const _this = this;
                _this.ruleForm.turl = 'http://47.96.141.103:8888/' + response.data.trim();
                console.log('添加图片' + _this.ruleForm.turl);
            },


        }
    }
</script>

<style>
    user-all{
        padding-top: 16px;
        margin-bottom: 16px;
        position: relative;
        word-wrap: break-word;
    }
    img-box{
        margin-bottom: 10px;
    }
    userpto{
        width: 50px;
        height: 50px;
        float: left;
        margin-right: 12px;
    }
    .use-info name{

        overflow: hidden;
        overflow-x: hidden;
        overflow-y: hidden;
        min-height: 42px;
    }
    name{
        vertical-align: middle;
        display: inline-block;
        margin-right: 2px;
        font-weight: 500;
    }
    single{
        margin-bottom: 12px;
        padding: 0 16px;
        position: relative;
        min-height: 50px;
    }
    words{
        padding: 0 16px 0;
        font-weight: normal;
        zoom: 1;
        margin: 0 0 8px;
    }

</style>