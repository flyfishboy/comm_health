<template>
    <div id="Header" style="background-color: #545c64;">
        <div style="">
            <el-menu
                    style="margin-left: 25%;height: 60px"
                    :default-active="activeIndex"
                    class="el-menu-demo"
                    mode="horizontal"
                    @select="handleSelect"
                    background-color="#545c64"
                    text-color="#fff"
                    active-text-color="#ffd04b">

                <el-menu-item>
                    <router-link to="/0">
                        健康生活交流平台
                    </router-link>
                </el-menu-item>
                <el-submenu index="2" v-if="name!=null">
                    <template slot="title">动态交流</template>
                    <el-menu-item style="font-size: 18px">
                        <router-link to="/Chat_Find" style="color: lavenderblush">查看动态</router-link>
                    </el-menu-item>
                    <el-menu-item style="font-size: 18px">
                        <router-link to="/Chat_Person" style="color: lavenderblush">个人查看</router-link>
                    </el-menu-item>
                    <el-menu-item style="font-size: 18px">
                        <router-link to="/Chat_Add" style="color: lavenderblush">发表动态</router-link>
                    </el-menu-item>
                </el-submenu>

                <el-submenu index="3" v-if="name!=null">
                    <template slot="title">制定生活计划</template>
                    <el-menu-item index="3-1">
                        <router-link style="color: lavenderblush" to="/Email">制定生活计划</router-link>
                    </el-menu-item>
                </el-submenu>

                <el-submenu index="4">
                    <template slot="title">小功能</template>
                    <el-menu-item index="4-1">
                        <router-link to="/Bmi" style="color: lavenderblush">BMI测量</router-link>
                    </el-menu-item>
                </el-submenu>

                <el-submenu index="7" >
                    <template slot="title">健康小常识</template>
                    <el-menu-item index="7-1">
                        <router-link to="/Member_Know" style="color: lavenderblush">健康小常识</router-link>
                    </el-menu-item>
                </el-submenu>

                <el-submenu index="5" v-if="name != null">
                    <template slot="title">聊天室</template>
                    <el-menu-item index="5-1">
                        <router-link to="/Room_Member" style="color: lavenderblush">寻找好友</router-link>
                    </el-menu-item>
                    <el-menu-item index="5-2">
                        <router-link to="/Room_History" style="color: lavenderblush">历史聊天</router-link>
                    </el-menu-item>
                    <el-menu-item index="5-3">
                        <router-link to="/Room_Alter" style="color: lavenderblush">消息提示</router-link>
                    </el-menu-item>
                </el-submenu>

                <el-submenu index="6" v-if="name!=null">
                    <template slot="title">{{name}}</template>
                    <el-menu-item index="6-1">
                        <router-link to="/Member_Person" style="color: lavenderblush">个人中心</router-link>
                    </el-menu-item>
                    <el-menu-item index="6-2" @click="exit">退出</el-menu-item>
                </el-submenu>
                <el-menu-item index="6" v-else>
                    <router-link to="/">注册/登录</router-link>
                </el-menu-item>
            </el-menu>
        </div>
    </div>

</template>
<script>
    export default {
        data() {
            return {
                activeIndex: '1',
                name: sessionStorage.getItem("name"),
            };
        },
        methods: {
            handleSelect(key, keyPath) {

            },
            exit() {
                window.sessionStorage.clear();
                this.$router.push("/");
                location.reload();
                this.name = null;
            },
        }
    }
</script>

<style>


</style>

