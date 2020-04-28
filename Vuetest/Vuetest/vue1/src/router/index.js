import Vue from 'vue'
import VueRouter from 'vue-router'
import Admin_Find   from '../views/Admin/Admin_Find'
import Admin_Manage from '../views/Admin/Admin_Manage'
import Admin_Add from '../views/Admin/Admin_Add'
import Admin_Update from '../views/Admin/Admin_Update'
import Admin_Person from '../views/Admin/Admin_Person'
import Menu from '../views/Admin/Menu'

import Member_Menu from '../views/Member/Member_Menu'
import Member_Person from '../views/Member/Member_Person'
import Login from '../views/Member/Login'
import Register from '../views/Member/Register'
import Home from '../views/Home/Home'

import Header from '../views/Home/Header'

import Page from '../views/Home/Page'
import Chat_Add from '../views/Home/Chat_Add'
import Page2 from '../views/Home/Page2'
import Page3 from '../views/Home/Page3'
import BMI from '../views/Home/BMI'
import Chat_Menu from '../views/Home/Chat_Menu'
import Chat_Find from '../views/Home/Chat_Find'
import Chat_Person from '../views/Home/Chat_Person'
import Chat_Update from '../views/Home/Chat_Update'
import Video_Show from '../views/Home/Video_Show'

Vue.use(VueRouter)

const routes = [
  {
    path:'/Home',
    component:Home,
    redirect:'/0',
    children:[
      {path:"/0",component:Page},
      {path:"/2",component:Page2},
      {path:"/Video_Show",component:Video_Show},
      {path:"/3",component:Page3},
      {path:"/4",component:Header},
      {path:"/Bmi",component:BMI},
      {path:'/Member_Menu', component:Member_Menu},
      {path:'/Member_Person', component:Member_Person},
      {path:'/Chat_Find',name:'查看动态',component:Chat_Find},
      {path:'/Chat_Person',name:'个人查看',component:Chat_Person},
      {path:'/Chat_Add',name:'发表动态',component:Chat_Add},
      {path:'/Chat_Update',name:'修改动态',component:Chat_Update}
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
    path:'/Admin_Update',
    component:Admin_Update,
    show:false
  }
];

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
