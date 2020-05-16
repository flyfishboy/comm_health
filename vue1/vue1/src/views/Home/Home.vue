<template>
  <div class="home">
    <pageheader></pageheader>
    <router-view></router-view>
  </div>
</template>

<script>
  import Header from './Header'
  import Page from './Page'

  import Page2 from './Email'
  import Chat_Find from './Chat_Find'

  export default {
    name: 'Home',
    components:{
      "pageheader":Header,
      "page":Page,
      "page2":Page2,
      // "page3":Page3,
      "Chat_Find":Chat_Find,

    }
  }
</script>
