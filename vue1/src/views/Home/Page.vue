
<template>
    <div>
        <el-carousel :interval="5000" arrow="always" class="d_jump" :height="imgHeight+'px'">
            <el-carousel-item v-for="item in imgList" :key="item.id">
                <el-row>
                    <el-col :span="1">
                        <img ref="imgHeight" :src="item.idView" class="banner_img" @load="imgLoad"/>
                    </el-col>
                </el-row>
            </el-carousel-item>
        </el-carousel>
        <div style="margin: 20px 0;"></div>
        <div class="title" >
            <el-link class="link1"  type="primary" href="https://voice.baidu.com/act/newpneumonia/newpneumonia/?from=osari_pc_3">
                疫情实时大数据报告
            </el-link>
        </div>
    </div>

</template>

<style>
    .title .link1 {
        font-size: 30px;
    }
    .el-carousel__item h3 {
        color: #475669;
        font-size: 14px;
        opacity: 0.75;
        line-height: 200px;
        margin: 0;
    }
    .banner_img{
        margin-left: 0%;
    }
    .el-carousel__item:nth-child(2n) {
        background-color: #d3dce6;
    }

    .el-carousel__item:nth-child(2n+1) {
        background-color: #d3dce6;
    }
</style>
<script>
    export default {
        data() {
            return{
                imgHeight:"",
                imgList: [ {id: 0, idView: require('../../assets/3.jpg')}, {id: 1, name: '详情', idView: require('../../assets/4.png')}, {id: 2, name: '推荐', idView: require('../../assets/2.jpg')},{id: 3, idView: require('../../assets/1.png')}, ]
            }
            that.imgHeight = '30px';
            window.onresize = function temp() { // 通过点语法获取img的height属性值
                that.imgHeight = `${that.$refs.imgHeight['0'].height}px` }

        },
        methods:{
            imgLoad(){
                this.$nextTick(()=>{
                    this.imgHeight=this.$refs.imgHeight[0].height

                })
            }
        }
        // ,
        // mounted(){
        //     this.imgLoad();
        //     window.addEventListener('resize',()=>{
        //         this.imgHeight=this.$refs.imgHeight[0].height;
        //         this.imgLoad();
        //     },false);
        // },

    }
</script>
 
 