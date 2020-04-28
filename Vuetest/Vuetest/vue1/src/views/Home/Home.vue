<template>
  <div class="home">
    <pageheader></pageheader>
    <router-view></router-view>
  </div>
</template>

<script>
  import Header from './Header'
  import Page from './Page'
  import Chat_Menu from './Chat_Menu'
  import Page2 from './Page2'
  import Page3 from './Page3'
  import Chat_Find from './Chat_Find'

  export default {
    name: 'Home',
    components:{
      "pageheader":Header,
      "page":Page,
      "Chat_Menu":Chat_Menu,
      "page2":Page2,
      "page3":Page3,
      "Chat_Find":Chat_Find,

    }
  }
</script>
