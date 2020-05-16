<template>
    <el-container style="height: 700px; border: 1px solid #eee">
        <el-aside width="200px" style="background-color: rgb(238, 241, 246)">

            <el-menu router :default-openeds="['0','1','2','3','4','5']"
                     @open="handleOpen"
                     @close="handleClose"
            >
                <el-submenu v-for="(item,index) in $router.options.routes" :index="index+''" v-if="item.show">
                    <template slot="title"><i class="el-icon-s-custom"></i>{{item.name}}</template>

                    <el-menu-item v-for="(item2,index2) in item.children" :index="item2.path"
                                  :class="$route.path==item2.path?'is-active':''">{{item2.name}}
                    </el-menu-item>
                </el-submenu>
            </el-menu>
        </el-aside> <!--左侧导航栏-->

        <el-container>
            <el-header style="text-align: right; font-size: 17px ">
                <el-dropdown>
                    <i class="el-icon-user" style="margin-right: 15px;font-size: 23px"></i>
                    <el-dropdown-menu slot="dropdown">
                        <el-dropdown-item @click.native="findP" style="font-size: 17px">查看<i class="el-icon-view el-icon--right"></i></el-dropdown-item>
                        <el-dropdown-item @click.native="out1" style="font-size: 17px">注销<i class="el-icon-warning el-icon--right"></i></el-dropdown-item>
                    </el-dropdown-menu>
                </el-dropdown>
                <span>{{name}}</span>
            </el-header>

            <el-main>
                <router-view></router-view>
            </el-main>
        </el-container>
    </el-container>
</template>

<script>
    export default {

        data() {
            const item = {
                date: '',
                name: '',
                address: ''
            };
            return {
                name: sessionStorage.getItem('name'),
                tableData: Array(20).fill(item)
            }
        },
        methods: {
            handleOpen(key, keyPath) {
                console.log(key, keyPath);
            },
            handleClose(key, keyPath) {
                console.log(key, keyPath);
            },
            findP() {
                this.$router.push("/Admin_Person")
            },
            // home1() {
            //     this.$router.push("/Home")
            // },
            out1() {
                window.sessionStorage.clear();
                this.$router.push("/")
                location.reload()
            }
        }
    }

</script>

<style>
    .el-header {
        background-color: #B3C0D1;
        color: #333;
        line-height: 60px;
    }

    .el-aside {
        color: #333;
    }
</style>