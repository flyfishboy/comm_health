<template>
    <div style="background-color: skyblue">
        <el-container style="height: 770px; border: 1px solid #eee">
            <!--左侧导航栏-->
            <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
                <el-row style="background-color:dimgray">
                    <el-col :span="16">
                        <h4 style="margin-left: 50%;color: whitesmoke">个人中心</h4>
                        <el-menu
                                align="center"
                                style="width: 199px;height: 704px"
                                default-active="1"
                                class="el-menu-vertical-demo"
                                @open="handleOpen"
                                @close="handleClose"
                                background-color="#545c64"
                                text-color="#fff"
                                active-text-color="#ffd04b">

                            <el-menu-item index="1">
                                <i class="el-icon-view"></i>
                                <router-link to="/Member_Person" style="color: lavenderblush"> 个人信息</router-link>
                            </el-menu-item>

                            <el-menu-item index="2">
                                <i class="el-icon-date"></i>
                                <router-link to="/Member_Bmi" style="color: lavenderblush"> BMI记录</router-link>
                            </el-menu-item>

                            <el-menu-item index="3">
                                <i class="el-icon-s-order"></i>
                                <router-link to="/Member_Email" style="color: lavenderblush"> 健康计划</router-link>
                            </el-menu-item>
                        </el-menu>
                    </el-col>
                </el-row>

            </el-aside>
            <!--左侧导航栏-->

            <el-container style="height: 112% ">

                <el-header style="text-align: right; font-size: 20px ">

                    <el-row class="block-col-2">
                        <el-col :span="12">
                            <el-dropdown trigger="click" style="margin-right: -100%">
                                <span class="el-dropdown-link">菜单<i class="el-icon-arrow-down el-icon--right"></i></span>
                                <el-dropdown-menu slot="dropdown">
                                    <el-dropdown-item @click.native="findP" style="font-size: 16px">查看<i class="el-icon-view el-icon--right"></i></el-dropdown-item>
                                    <el-dropdown-item @click.native="out1" style="font-size: 16px">注销<i class="el-icon-warning el-icon--right"></i> </el-dropdown-item>
                                </el-dropdown-menu>
                            </el-dropdown>
                        </el-col>
                    </el-row>

                    <!--<el-dropdown>-->

                    <!--<el-avatar :src="turl"></el-avatar>-->
                    <!--<el-dropdown-menu slot="dropdown">-->

                    <!--<el-dropdown-item @click.native="findP">查看<i class="el-icon-view el-icon&#45;&#45;right"></i></el-dropdown-item>-->
                    <!--<el-dropdown-item @click.native="out1">注销</el-dropdown-item>-->

                    <!--</el-dropdown-menu>-->

                    <!--</el-dropdown>-->

                    <!--<span style="margin-left: 1%;">{{name}}</span>-->

                </el-header>

                <el-main>
                    <router-view></router-view>
                </el-main>

            </el-container>

        </el-container>
    </div>
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
                tableData: Array(20).fill(item),
                turl: sessionStorage.getItem('turl')
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
                this.$router.push("/Member_Person")
            },
            out1() {
                window.sessionStorage.clear();
                this.$router.push("/");
                location.reload()
            }
        }
    }

</script>

<style>
     .el-dropdown-link {
        cursor: pointer;
        color: antiquewhite;
         font-size: 20px;
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

</style>