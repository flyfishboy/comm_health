
<template>
    <div  class="user-all" align="center" style="background-color: darkgray">
        <el-card v-for="item in tableData" style="width: 60%" align="left">
            <div class="userpto">
                <el-avatar src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"></el-avatar>
                <a target="_blank" href="/Chat_Person" data-clicklog="nick" class="name">{{item.name}}</a>

                <span style="color: dimgray;margin-left: 65%">{{item.time}}</span>
            </div>
            <!--显示动态内容-->

            <!--文字内容-->
            <div style="margin-top: 20px">
                <span style=" font-size: 15px;">{{item.words}}</span>

                <!--图片视频内容-->
                <div style="margin-top: 20px">
                    <div v-if="item.vurl !=''>0 ">
                      <el-button style="width: 15%" type="success" plain @click="Sub(item)"> 查看视频</el-button>
                    </div>

                    <div v-else-if="item.url !=''>0">
                        <div class="imga">
                            <el-image :src="item.url" class="image"></el-image>
                        </div>
                    </div>
                    <div v-else>
                    </div>
                </div>
            </div>

            <!--回复-->
            <div>
                <el-popover
                        placement="right"
                        width="400"
                        style="margin-left: 88%"
                        trigger="click">

                    <div>
                        <el-form  :model="ruleForm"  ref="ruleForm"  class="demo-ruleForm">
                            <el-header v-show="false"   class="header" prop="time2" v-model="ruleForm.time2"  >
                            </el-header>
                            <el-form-item  prop="text">
                                <el-input
                                        type="textarea"
                                        :autosize="{ minRows: 2, maxRows: 4}"
                                        placeholder="说点什么吧..."
                                        v-model="ruleForm.text">
                                </el-input>
                            </el-form-item>

                            <el-form-item style="margin-top: 10px;margin-left:80%">
                                <el-button type="primary" @click="submitForm('ruleForm',item.id)">发送</el-button>
                            </el-form-item>

                        </el-form>
                    </div>
                    <el-button slot="reference">回复</el-button>
                </el-popover>
            </div>

            <!--回复内容显示-->
            <div>
                <el-button style="margin-top: -40px" @click="findre(item.id)">查看回复</el-button>
                <div>
                    <el-divider content-position="right"></el-divider>
                    <div style="height: 5px"></div>
                    <div style=" background-color: whitesmoke;">
                    <div v-for="item2 in tableData2" v-if="item.id == item2.rid"  style="width: 90%;margin-left: 3%;margin-top: 2px" align="left">
                        <span style="font-size: 1px;">{{item2.time2}}--</span>
                        <span  style="font-family: 'Hiragino Sans GB'; font-size: 13px; color: cadetblue; font-weight: bolder">{{item2.name}}:   </span>
                        <span style=" font-size: 10px;">    {{item2.text}}</span>

                    </div>
                    </div>
                </div>
            </div>
            <div style="margin: 20px 0;"></div>
        </el-card>

        <el-pagination
                background
                layout="prev, pager, next"
                :page-size="pageSize"
                :total="total"
                @current-change="page1">
        </el-pagination>
    </div>
</template>

<script>

    export default {
        created(){
            const _this = this;
            axios.get('http://localhost:8181/chat/findAll/0/6').then(function (resp) {
                console.log(resp);
                _this.tableData = resp.data.content;
                _this.pageSize = resp.data.size;
                _this.total = resp.data.totalElements;

                // console.log(resp.data.content.v_url);
            });
        },
        inject:['reload'],                                 //注入App里的reload方法
        data(){
            return {

            ruleForm:{
                    text:'',
                    time2:'',
                    name:sessionStorage.getItem('name'),
                    rid:0,
                },
                nowDate: "",    // 当前日期
                nowTime: "",    // 当前时间
                name:sessionStorage.getItem('name'),
                time:sessionStorage.getItem('time'),
                pageSize: 0,
                total: 0,
                tableData: [],
                tableData2:[],

            }
        },

        methods: {
            // 将视频地址传到下个页面播放
            Sub(item) {
                window.sessionStorage.setItem("vvurl",item.vurl);
                // console.log(item);
                this.$router.push('/Video_Show')
            },
            // 查看回复
            findre(item) {
                const _this = this;
                axios.get('http://localhost:8181/reply/find/' + item).then(function (resp) {
                    console.log(resp);
                    _this.tableData2 = resp.data;
                })
            },
            // 分页
            page1(currentPage) {
                const _this = this;
                axios.get('http://localhost:8181/chat/findAll/' + (currentPage - 1) + '/6').then(function (resp) {
                    console.log(resp);
                    _this.tableData = resp.data.content;
                    _this.pageSize = resp.data.size;
                    _this.total = resp.data.totalElements;
                })
            },

            // 提交回复
            submitForm(formName,id) {
                const _this = this;
                _this.ruleForm.rid = id;
                axios.post('http://localhost:8181/reply/save', this.ruleForm).then(function (resp) {
                if (resp.data == 'success') {
                        _this.$message({message: '已回复', type: 'success'});
                        _this.reload();
                    } else {
                        _this.$message.error('回复失败');
                    }
                })
            },
            // 获取当前时间
            currentTime() {
                setInterval(this.getDate, 500);
            },
            getDate: function() {
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

                _this.nowTime = hh + ":" + mf;
                _this.nowDate = yy + "/" + mm + "/" + dd;
                _this.ruleForm.time2 =_this.nowDate+ ' '+_this.nowTime;
            }
        },
        mounted() {
            this.currentTime();
        },

    }

</script>

<style>
    .video-js .vjs-big-play-button {
        width: 72px;
        height: 72px;
        border-radius: 100%;
        z-index: 100;
        background-color: #ffffff;
        border: solid 1px #979797;
    }

    .text {
        font-size: 14px;
    }

    .item {
        padding: 18px 0;
    }

    .box-card {
        width: 480px;
    }
    .imga img {
        width: 400px;
        height: 300px;
    }
    .vi video{
        width: 400px;
        height: 300px;
    }
    .el-dropdown-link {
        cursor: pointer;
        color: #409EFF;
    }
    .el-icon-arrow-down {
        font-size: 12px;
    }
    .demonstration {
        display: block;
        color: #8492a6;
        font-size: 14px;
        margin-bottom: 20px;
    }
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