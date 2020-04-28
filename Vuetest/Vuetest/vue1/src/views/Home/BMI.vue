<template>
    <div style="background-color: darkgray; height: 800px">
    <div class="box" >
        <h2>免费计算你的身高质量指数（BMI）</h2>
        <hr />
        <form action="" method="post">
            我的身高:<input type="number" id="height"/>单位：厘米
            <br />
            <hr />
            我的体重:<input type="number" id="weight" />单位：千克
            <br />
            <hr />
            <input type="button" value="计算" @click="com()"/>
        </form>

        <table class="table1">
            <tr><th>分类</th><th>BMI范围</th> </tr>
            <tr><td>偏瘦</td><td><=18.4</td></tr>
            <tr><td>正常</td><td>18.5~23.9</td></tr>
            <tr><td>过重</td><td>24.0~27.9</td></tr>
            <tr><td>肥胖</td><td>>=28.0</td></tr>
        </table>
        <table id="res_table">
            <tr><th class="time_">日期</th>
                <th>身高</th>
                <th>体重</th>
                <th>BMI值</th>
            </tr>

        </table>
    </div>
    </div>
</template>

<script>
    export default {
        data(){
        return{
        com(){
        //1.先获取身高
        const height1 = document.getElementById("height").value;
        //2.获取体重
        const weight = document.getElementById("weight").value;
        const height = height1/100;
        //3.计算值
        const bmi = (weight/(height*height)).toFixed(2);
        console.log(bmi)
        //4.获取日期
        const $date = new Date();
        const $time = $date.toLocaleString();
        //5.显示在页面中
        const table_ = document.getElementById("res_table");
        const tr_ = document.createElement("tr");
        //获取table中tr的个数
        const len = table_.children.length;
        if(len == 1){
            table_.appendChild(tr_);
        }else{
            table_.insertBefore(tr_,table_.children[1]);
        }

        const td1 = document.createElement("td");
        td1.innerHTML = $time;
        tr_.appendChild(td1);

        const td2 = document.createElement("td");
        td2.innerHTML = height*100;
        tr_.appendChild(td2);

        const td3 = document.createElement("td");
        td3.innerHTML = weight;
        tr_.appendChild(td3);

        const td4 = document.createElement("td");
        td4.innerHTML = bmi;
        tr_.appendChild(td4);

    }
    }
        }
    }
</script>

<style scoped>
    *{
        margin: 0;
        padding: 0;
    }

    body{
        background: skyblue;
    }

    .box{
        width: 800px;
        margin: 0 auto;
        text-align: center;
    }

    .box h2{
        height: 60px;
        line-height: 60px;
    }
    .box hr{
        margin: 20px 0;
    }

    .box input[type="number"]{
        width: 240px;
        height: 28px;
        margin: 0 10px;
    }

    .box input[type="button"]{
        width: 260px;
        height: 40px;
        color: white;
        font-size: 18px;
        background: orange;
        border: none;
    }

    .box .table1{
        width:800px ;
        height: 200px;
        margin-top: 20px;

    }
    .table1 tr:nth-child(1){
        background: cyan;
    }

    .table1 tr:nth-child(2){
        background: yellowgreen;
    }

    .table1 tr:nth-child(3){
        background: green;
    }

    .table1 tr:nth-child(4){
        background: orange;
    }

    .table1 tr:nth-child(5){
        background: red;
    }

    .box #res_table{
        width: 800px;
        background: palevioletred;
        margin-top: 30px;
    }

    #res_table tr:nth-child(2n){
        background: yellowgreen;
    }

    #res_table  .time_{
        width: 260px;
    }
</style>