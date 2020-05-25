import Vue from 'vue'
import VueRouter from 'vue-router'
import Admin_Find   from '../views/Admin/Admin_Find'
import Admin_Manage from '../views/Admin/Admin_Manage'
import Admin_Add from '../views/Admin/Admin_Add'
import Admin_Message from "../views/Admin/Admin_Message";
import Admin_Update from '../views/Admin/Admin_Update'
import Admin_Person from '../views/Admin/Admin_Person'
import Menu from '../views/Admin/Menu'
import Message_Add from '../views/Admin/Message_Add'

import Member_Menu from '../views/Member/Member_Menu'
import Member_Person from '../views/Member/Member_Person'
import Member_Update from '../views/Member/Member_Update'
import Member_Bmi from '../views/Member/Member_Bmi'
import Member_Email from '../views/Member/Member_Email'
import Member_Know from '../views/Member/Member_Know'
import Login from '../views/Member/Login'
import Register from '../views/Member/Register'

import Home from '../views/Home/Home'
import Header from '../views/Home/Header'
import Page from '../views/Home/Page'
import Chat_Add from '../views/Home/Chat_Add'
import Email from '../views/Home/Email'
import BMI from '../views/Home/BMI'
import Chat_Find from '../views/Home/Chat_Find'
import Chat_Person from '../views/Home/Chat_Person'
import Chat_Update from '../views/Home/Chat_Update'
import Video_Show from '../views/Home/Video_Show'

import Room_Member from '../views/Room/Room_Member'
import Room_Comm from '../views/Room/Room_Comm'
import Room_History from "../views/Room/Room_History";
import Room_Alter from "../views/Room/Room_Alter";


Vue.use(VueRouter)


const routes = [
  {
    path:'/Home',
    component:Home,
    redirect:'/0',
    children:[
      {path:"/0",name:'用户主页',component:Page},
      {path:"/Email",name:'邮箱',component:Email},
      {path:"/Video_Show",name:'播放视频',component:Video_Show},
      {path:"/4",name:'页面顶部导航',component:Header},
      {path:"/Bmi",name:'Bmi界面',component:BMI},
      {
        path:'/Member_Menu',
        component:Member_Menu,
        children:[
          {path:'/Member_Person',name:'个人信息', component:Member_Person},
          {path:'/Member_Update',name:'修改个人信息', component:Member_Update},
          {path:'/Member_Bmi',name:'Bmi记录', component:Member_Bmi},
          {path:'/Member_Email',name:'邮箱记录', component:Member_Email},
        ]},
      {path:'/Member_Know',name:'小常识', component:Member_Know},
      {path:'/Chat_Find',name:'查看动态',component:Chat_Find},
      {path:'/Chat_Person',name:'个人查看',component:Chat_Person},
      {path:'/Chat_Add',name:'发表动态',component:Chat_Add},
      {path:'/Chat_Update',name:'修改动态',component:Chat_Update},
      {path:'/Room_Member',name:'聊天室成员',component:Room_Member},
      {path:'/Room_Comm',name:'聊天界面', component:Room_Comm},
      {path:'/Room_History',name:'聊天历史', component:Room_History},
      {path:'/Room_Alter',name:'聊天信息提醒', component:Room_Alter},
    ]
  },

  {
    path: '/',
    name: 'Login',
    component: Login
  },
  {
    path: '/Register',
    name: 'Register',
    component: Register
  },
  {
    path:'/Menu',
    name:"个人信息",
    component:Menu,
    show:true,
    redirect:'/Admin_Find',
    children:[
      {
        path: '/Admin_Person',
        name:"个人信息",
        component:Admin_Person
      }
    ]
  },
  {
    path:'/Menu',
    name:"会员信息管理",
    component:Menu,
    show:true,
    redirect:'/Admin_Find',
    children:[
      {
        path: '/Admin_Manage',
        name:"会员信息",
        component:Admin_Manage
      },
      {
        path:'/Admin_Add',
        name:"添加会员",
        component:Admin_Add
      }
    ]
  },
  {
    path:'/Menu',
    name:"管理员信息",
    component:Menu,
    show:true,
     redirect:'/Admin_Find',
    children:[
      {
        path: '/Admin_Find',
        name:"管理员信息",
        component:Admin_Find
      }
    ]
  },
  {
    path:'/Menu',
    name:"健康小常识",
    component:Menu,
    show:true,
    redirect:'/Admin_Message',
    children:[
      {
        path: '/Admin_Message',
        name:"健康小常识",
        component:Admin_Message
      },
      {
        path: '/Message_Add',
        name:"添加内容",
        component:Message_Add
      }
    ]
  },
  {
    path:'/Admin_Update',
    component:Admin_Update,
    show:false
  },
];
const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
});



export default router
