<!--<template>-->
    <!--<el-container style="height: 500px; border: 1px solid #eee">-->

        <!--<el-row class="tac">-->
        <!--<el-col :span="40">-->
            <!--<h5 align="center" style="color: gray;">动态浏览</h5>-->
            <!--<el-menu-->
                    <!--router :default-openeds="['0','1','2','3']"-->
                    <!--style="width: 200px"-->
                    <!--default-active="1"-->
                    <!--class="el-menu-vertical-demo"-->
                    <!--@open="handleOpen"-->
                    <!--@close="handleClose"-->
                    <!--background-color="#545c64"-->
                    <!--text-color="#fff"-->
                    <!--active-text-color="#ffd04b">-->
                <!--<el-submenu index="1" >-->
                    <!--<template slot="title">-->
                        <!--<i class="el-icon-location"></i>-->
                        <!--<span>动态</span>-->
                    <!--</template>-->
                    <!--<el-menu-item-group >-->
                        <!--<el-menu-item  style="font-size: 18px"><router-link to="/Chat_Find" style="color: lavenderblush">查看动态</router-link></el-menu-item>-->
                        <!--<el-menu-item  style="font-size: 18px"><router-link to="/Chat_Person" style="color: lavenderblush">个人查看</router-link></el-menu-item>-->
                        <!--<el-menu-item  style="font-size: 18px"><router-link to="/Chat_Add" style="color: lavenderblush">发表动态</router-link></el-menu-item>-->
                        <!--<el-menu-item  style="font-size: 18px"><router-link to="/Chat_Update" style="color: lavenderblush">修改动态</router-link></el-menu-item>-->
                    <!--</el-menu-item-group>-->
                    <!--&lt;!&ndash;<el-submenu index="1-4">&ndash;&gt;-->
                        <!--&lt;!&ndash;<template slot="title">选项4</template>&ndash;&gt;-->
                        <!--&lt;!&ndash;<el-menu-item index="1-4-1">选项1</el-menu-item>&ndash;&gt;-->
                    <!--&lt;!&ndash;</el-submenu>&ndash;&gt;-->
                <!--</el-submenu>-->
                <!--&lt;!&ndash;<el-menu-item index="2">&ndash;&gt;-->
                    <!--&lt;!&ndash;<i class="el-icon-menu"></i>&ndash;&gt;-->
                    <!--&lt;!&ndash;<span slot="title">导航二</span>&ndash;&gt;-->
                <!--&lt;!&ndash;</el-menu-item>&ndash;&gt;-->


            <!--</el-menu>-->
        <!--</el-col>-->
        <!--</el-row>&lt;!&ndash;左侧导航栏&ndash;&gt;-->

        <!--<el-container>-->
            <!--<el-header style="text-align: right; font-size: 17px ">-->
                <!--<el-dropdown>-->
                    <!--<i class="el-icon-user" style="margin-right: 15px"></i>-->
                    <!--<el-dropdown-menu slot="dropdown">-->
                        <!--<el-dropdown-item @click.native="findP">查看</el-dropdown-item>-->
                        <!--<el-dropdown-item @click.native="home1">主页面</el-dropdown-item>-->
                        <!--<el-dropdown-item @click.native="out1">注销</el-dropdown-item>-->
                    <!--</el-dropdown-menu>-->
                <!--</el-dropdown>-->
                <!--<span>{{name}}</span>-->
            <!--</el-header>-->

            <!--<el-main>-->
                <!--<router-view></router-view>-->
            <!--</el-main>-->
        <!--</el-container>-->
    <!--</el-container>-->
<!--</template>-->

<!--<script>-->
    <!--export default {-->

        <!--data() {-->
            <!--const item = {-->
                <!--date: '',-->
                <!--name: '',-->
                <!--address: ''-->
            <!--};-->
            <!--return {-->
                <!--name:sessionStorage.getItem('name'),-->
                <!--tableData: Array(20).fill(item)-->
            <!--}-->
        <!--},-->
        <!--methods: {-->
            <!--handleOpen(key, keyPath) {-->
                <!--console.log(key, keyPath);-->
            <!--},-->
            <!--handleClose(key, keyPath) {-->
                <!--console.log(key, keyPath);-->
            <!--},-->
            <!--findP() {-->
                <!--this.$router.push("/Chat_Person")-->
            <!--},-->
            <!--home1() {-->
                <!--this.$router.push("/Home")-->
            <!--},-->
            <!--out1(){-->
                <!--window.sessionStorage.clear();-->
                <!--this.$router.push("/")-->
                <!--location.reload()-->
            <!--}-->
        <!--}-->
    <!--}-->

<!--</script>-->

<!--<style>-->

<!--</style>-->