<template>
    <div id="Header" >
        <div class="line" ></div>
        <el-menu
                :default-active="activeIndex"
                class="el-menu-demo"
                mode="horizontal"
                @select="handleSelect"
                background-color="#545c64"
                text-color="#fff"
                active-text-color="#ffd04b">
            <el-menu-item >
                <router-link to="/0">
                健康生活交流平台
                </router-link>
            </el-menu-item>
            <el-submenu index="2">
                <template slot="title">动态交流 </template>
                <el-menu-item  style="font-size: 18px"><router-link to="/Chat_Find" style="color: lavenderblush">查看动态</router-link></el-menu-item>
                <el-menu-item  style="font-size: 18px"><router-link to="/Chat_Person" style="color: lavenderblush" >个人查看</router-link></el-menu-item>
                <el-menu-item  style="font-size: 18px"><router-link to="/Chat_Add" style="color: lavenderblush">发表动态</router-link></el-menu-item>

            </el-submenu>
            <el-menu-item index="3" ><router-link to="/2">测试</router-link></el-menu-item>
            <el-submenu index="4">
                <template slot="title">小功能 </template>
                <el-menu-item index="4-1"><router-link to="/Bmi" style="color: lavenderblush">BMI测量</router-link></el-menu-item>
                <el-menu-item index="4-2"><router-link to="/3" style="color: lavenderblush">选项2</router-link></el-menu-item>
                <el-menu-item index="4-3">选项3</el-menu-item>
            </el-submenu>
            <el-submenu index="5" v-if="name!=null">
                <template slot="title">{{name}}</template>
                <el-menu-item index="5-1"><router-link to="/Member_Person" style="color: lavenderblush">个人中心</router-link></el-menu-item>
                <el-menu-item index="5-2" @click="exit">退出</el-menu-item>
            </el-submenu>
            <el-menu-item index="6" v-else><router-link to="/">注册/登录</router-link></el-menu-item>
        </el-menu>
    </div>
</template>
<script>
    export default {
        data() {
            return {
                activeIndex: '1',
                name:sessionStorage.getItem("name"),
            };
        },
        methods: {
            handleSelect(key, keyPath) {

            },
            exit(){
                window.sessionStorage.clear();
                this.$router.push("/");
                location.reload();
                this.name=null;
            },
        }
    }
</script>

<style>


</style>

