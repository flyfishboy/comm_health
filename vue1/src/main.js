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

Vue.config.productionTip = false;

// 定义聊天室默认下滑框为底部
import VueScroller from "vue-scroller"
Vue.use(VueScroller);


new Vue({
  router,
  store,
  render: h => h(App),

}).$mount('#app')


