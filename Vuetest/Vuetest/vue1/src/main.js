import Vue from 'vue'
import './plugins/axios'
import App from './App.vue'
import router from './router'
import store from './store'
import './plugins/element.js'

import VideoPlayer from 'vue-video-player'
require('video.js/dist/video-js.css');
require('vue-video-player/src/custom-theme.css');
Vue.use(VideoPlayer);


// // 引入路由
// import VueRouter from 'vue-router'
// // 引入路由的配置文件routes.js
// import Routes from './routes'

// Vue.prototype.$axios = axios;

Vue.config.productionTip = false;

// // 使用声明
// Vue.use(VueRouter);
// // 创建路由
// const router = new VueRouter({
//   routes:Routes,
//   // 消除地址栏中的“#”
//   mode:"history"
// });


new Vue({
  router,
  store,
  render: h => h(App),

}).$mount('#app')


