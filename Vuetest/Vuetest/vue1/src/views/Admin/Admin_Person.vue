<template>
    <div class="login-container">

        <body>
        <table width="500" border="0" align="center">
            <tr>
                <td colspan="2" style="background-color: aliceblue;" align="center">
                    <h1 >个人信息</h1>
                </td>
            </tr>
            <tr>
            <h1>姓名</h1>
            <td style="font: 18px large">{{name}}</td>
            </tr>
            <tr>
                <h1>账户</h1>
                <td style="font: 18px large">{{account}}</td>
            </tr>
            <tr>
                <h1>电话号码</h1>
                <td style="font: 18px large">{{tel}}</td>
            </tr>

        </table>
        </body>
    </div>
</template>


<script>
    export default {
        data() {
            return {
                imgSrc:require('../../assets/login.jpg'),
                name:sessionStorage.getItem('name'),
                account:sessionStorage.getItem('account'),
                password:sessionStorage.getItem('password'),
                tel:sessionStorage.getItem('tel'),
             };
        },
    }
</script>

<style>

</style>