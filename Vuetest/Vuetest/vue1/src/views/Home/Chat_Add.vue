
<template>
    <div align="center" style="background-color: darkgray">
        <el-form :model="ruleForm" ref="ruleForm" label-width="80px" >
            <el-header   class="header" prop="time" v-model="ruleForm.time" style="background-color: white" >
                <div>
                    {{ nowDate + ' ' + nowTime + ' ' + nowWeek }}
                </div>
            </el-header>
            <el-input
                    prop="words"
                    style="width: 360px"
                    type="textarea"
                    autosize
                    placeholder="请输入内容"
                    v-model="ruleForm.words">
            </el-input>
            <div style="height: 5px"></div>
            <el-tabs type="border-card">
                <el-tab-pane label="发送图片">
                    <el-upload
                            prop="url"
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
                            v-model="ruleForm.url"
                            multiple>
                        <i class="el-icon-upload"></i>
                        <div class="el-upload__text">将图片拖到此处，或<em>点击上传</em></div>
                        <div class="el-upload__tip" slot="tip">只能上传jpg/png文件，且不超过2Mb</div>
                    </el-upload>
                    <el-form-item>
                        <el-button type="primary" @click="onSubmit('ruleForm')">发表</el-button>
                        <el-button>取消</el-button>
                    </el-form-item>
                </el-tab-pane>

                <el-tab-pane label="发送视频">
                    <el-upload
                            prop="vurl"
                            class="upload-demo"
                            drag
                            ref="upload"
                            action="http://localhost:8181/upload"
                            accept="video/mp4, video/ogg,video/flv,video/avi,video/wmv,video/rmvb"
                            :before-upload="handleBeforeUpload2"
                            :on-preview="handlePreview"
                            :on-remove="handleRemove"
                            :on-success="handleAvatarSuccess2"
                            name="myFile"
                            list-type="video"
                            v-model="ruleForm.vurl"
                            multiple>
                        <i class="el-icon-upload"></i>
                        <div class="el-upload__text">将视频拖到此处，或<em>点击上传</em></div>
                        <div class="el-upload__tip" slot="tip">能上传视频文件，且不超过10Mb</div>
                    </el-upload>
                    <el-form-item>
                        <el-button type="primary" @click="onSubmit('ruleForm')">发表</el-button>
                        <el-button>取消</el-button>
                    </el-form-item>
                </el-tab-pane>
            </el-tabs>

        </el-form>

    </div>

</template>

<script>
    export default {
        data(){
            return{
                nowDate: "",    // 当前日期
                nowTime: "",    // 当前时间
                nowWeek: "",    // 当前星期
                time:'',
                ruleForm: {
                    words: '',
                    name:window.sessionStorage.getItem("name"),
                    url:'',
                    vurl:'',
                    time:'',
                    account:window.sessionStorage.getItem("account")
                }
            }

        },
        methods: {
            handleBeforeUpload(file) {
                const isLt2M = file.size / 1024 / 1024 < 2;
                if (!isLt2M) {
                    this.$message.error('上传文件大小不能超过 2MB!')
                }
                return isLt2M
            },
            handleBeforeUpload2(file) {
                const isLt2M = file.size / 1024 / 1024 < 10;
                if (!isLt2M) {
                    this.$message.error('上传文件大小不能超过 10MB!')
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
                _this.ruleForm.url = 'http://47.96.141.103:8888/' + response.data.trim();
                console.log('添加图片' + _this.ruleForm.url);

            },
            handleAvatarSuccess2(response) { //上传视频成功的函数
                const _this = this;
                _this.ruleForm.vurl = 'http://47.96.141.103:8888/' + response.data.trim();
                console.log('添加视频' + _this.ruleForm.vurl);
            },

            onSubmit(formName) {
                const _this = this;
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        axios.post('http://localhost:8181/chat/save', this.ruleForm).then(function (resp) {
                            if (resp.data == 'success') {
                                window.sessionStorage.setItem('time', _this.ruleForm.time);
                                _this.$alert('' + _this.ruleForm.name + '  发表成功！', '消息', {
                                    confirmButtonText: '确定',
                                    callback: action => {
                                        _this.$router.push('/Chat_Find')
                                    }
                                })
                            } else {
                                _this.$message('发表失败');
                            }
                        })
                    } else {
                        return false;
                    }
                });
            },
            currentTime() {
                setInterval(this.getDate, 500);
            },
            getDate: function () {
                var _this = this;
                let yy = new Date().getFullYear();
                let mm = new Date().getMonth() + 1;
                let dd = new Date().getDate();
                let week = new Date().getDay();
                let hh = new Date().getHours();
                let mf =
                    new Date().getMinutes() < 10
                        ? "0" + new Date().getMinutes()
                        : new Date().getMinutes();
                if (week == 1) {
                    _this.nowWeek = "星期一";
                } else if (week == 2) {
                    _this.nowWeek = "星期二";
                } else if (week == 3) {
                    _this.nowWeek = "星期三";
                } else if (week == 4) {
                    _this.nowWeek = "星期四";
                } else if (week == 5) {
                    _this.nowWeek = "星期五";
                } else if (week == 6) {
                    _this.nowWeek = "星期六";
                } else {
                    _this.nowWeek = "星期日";
                }
                _this.nowTime = hh + ":" + mf;
                _this.nowDate = yy + "/" + mm + "/" + dd;
                _this.ruleForm.time = _this.nowDate + ' ' + _this.nowTime + ' ' + _this.nowWeek;
            }
        },
        mounted() {
            this.currentTime();
        }

    }
</script>

<style>

</style>


<!--<template>-->
    <!--<div align="center">-->
    <!--<el-form :model="ruleForm" ref="ruleForm" label-width="80px" >-->
        <!--<el-header   class="header" prop="time" v-model="ruleForm.time" style="background-color: white" >-->
            <!--<div>-->
                <!--{{ nowDate + ' ' + nowTime + ' ' + nowWeek }}-->
            <!--</div>-->
        <!--</el-header>-->
        <!--<el-input-->
                <!--prop="words"-->
                <!--style="width: 360px"-->
                <!--type="textarea"-->
                <!--autosize-->
                <!--placeholder="请输入内容"-->
                <!--v-model="ruleForm.words">-->
        <!--</el-input>-->
        <!--<el-upload-->
                <!--prop="url"-->
                <!--class="upload-demo"-->
                <!--drag-->
                <!--ref="upload"-->
                <!--action="http://localhost:8181/upload"-->
                <!--accept="image/png,image/gif,image/jpg,image/jpeg"-->
                <!--:before-upload="handleBeforeUpload"-->
                <!--:on-preview="handlePreview"-->
                <!--:on-remove="handleRemove"-->
                <!--:on-success="handleAvatarSuccess"-->
                <!--name="myFile"-->
                <!--list-type="picture"-->
                <!--v-model="ruleForm.url"-->
                <!--multiple>-->
            <!--<i class="el-icon-upload"></i>-->
            <!--<div class="el-upload__text">将图片拖到此处，或<em>点击上传</em></div>-->
            <!--<div class="el-upload__tip" slot="tip">只能上传jpg/png文件，且不超过2Mb</div>-->
        <!--</el-upload>-->
        <!--<el-form-item>-->
            <!--<el-button type="primary" @click="onSubmit('ruleForm')">发表</el-button>-->
            <!--<el-button>取消</el-button>-->
        <!--</el-form-item>-->

    <!--</el-form>-->

    <!--</div>-->

<!--</template>-->

<!--<script>-->
    <!--export default {-->
        <!--data(){-->
            <!--return{-->
                <!--nowDate: "",    // 当前日期-->
                <!--nowTime: "",    // 当前时间-->
                <!--nowWeek: "",    // 当前星期-->
                <!--time:'',-->
                <!--ruleForm: {-->
                    <!--words: '',-->
                    <!--name:window.sessionStorage.getItem("name"),-->
                    <!--url:'',-->
                    <!--time:'',-->
                    <!--account:window.sessionStorage.getItem("account")-->
                <!--}-->
            <!--}-->

        <!--},-->
        <!--methods: {-->
            <!--handleBeforeUpload(file) {-->
                <!--const isLt2M = file.size / 1024 / 1024 < 2-->
                <!--if (!isLt2M) {-->
                    <!--this.$message.error('上传图片大小不能超过 2MB!')-->
                <!--}-->
                <!--return isLt2M-->
            <!--},-->
            <!--handleRemove(file, fileList) {-->
                <!--console.log(file, fileList);-->
            <!--},-->
            <!--handlePreview(file) {-->
                <!--console.log(file);-->
            <!--},-->

            <!--handleAvatarSuccess(response) { //第一个上传的图片的成功的函数-->
                <!--const _this = this;-->
                <!--_this.ruleForm.url = 'http://47.96.141.103:8888/' + response.data.trim();-->
                <!--console.log('添加图片' + _this.ruleForm.url);-->
                <!--console.log(this.name)-->
            <!--},-->

            <!--onSubmit(formName) {-->
                <!--const _this = this;-->
                <!--this.$refs[formName].validate((valid) => {-->
                    <!--if (valid) {-->
                        <!--axios.post('http://localhost:8181/chat/save', this.ruleForm).then(function (resp) {-->
                            <!--if (resp.data == 'success') {-->
                                <!--console.log(resp);-->
                                <!--console.log(_this.ruleForm.url);-->
                                <!--console.log(_this.ruleForm.time);-->
                                <!--window.sessionStorage.setItem('time', _this.ruleForm.time);-->

                                <!--_this.$alert('' + _this.ruleForm.name + '  发表成功！', '消息', {-->
                                    <!--confirmButtonText: '确定',-->
                                    <!--callback: action => {-->
                                        <!--_this.$router.push('/Chat_Find')-->
                                    <!--}-->
                                <!--})-->
                            <!--} else {-->
                                <!--_this.$message('发表失败');-->
                            <!--}-->
                        <!--})-->
                    <!--} else {-->
                        <!--return false;-->
                    <!--}-->
                <!--});-->
            <!--},-->
            <!--currentTime() {-->
                <!--setInterval(this.getDate, 500);-->
            <!--},-->
            <!--getDate: function () {-->
                <!--var _this = this;-->
                <!--let yy = new Date().getFullYear();-->
                <!--let mm = new Date().getMonth() + 1;-->
                <!--let dd = new Date().getDate();-->
                <!--let week = new Date().getDay();-->
                <!--let hh = new Date().getHours();-->
                <!--let mf =-->
                    <!--new Date().getMinutes() < 10-->
                        <!--? "0" + new Date().getMinutes()-->
                        <!--: new Date().getMinutes();-->
                <!--if (week == 1) {-->
                    <!--_this.nowWeek = "星期一";-->
                <!--} else if (week == 2) {-->
                    <!--_this.nowWeek = "星期二";-->
                <!--} else if (week == 3) {-->
                    <!--_this.nowWeek = "星期三";-->
                <!--} else if (week == 4) {-->
                    <!--_this.nowWeek = "星期四";-->
                <!--} else if (week == 5) {-->
                    <!--_this.nowWeek = "星期五";-->
                <!--} else if (week == 6) {-->
                    <!--_this.nowWeek = "星期六";-->
                <!--} else {-->
                    <!--_this.nowWeek = "星期日";-->
                <!--}-->
                <!--_this.nowTime = hh + ":" + mf;-->
                <!--_this.nowDate = yy + "/" + mm + "/" + dd;-->
                <!--_this.ruleForm.time = _this.nowDate + ' ' + _this.nowTime + ' ' + _this.nowWeek;-->
            <!--}-->
        <!--},-->
        <!--mounted() {-->
            <!--this.currentTime();-->
        <!--}-->

    <!--}-->
<!--</script>-->

<!--<style>-->

<!--</style>-->