<template>
  <v-app>
    <Navbar />
    <Nprogress />
    <router-view />  
    <Footer />
  </v-app>
</template>

<script>  
import Footer from '../components/Common/Footer.vue'
import Navbar from '../components/Common/Navbar.vue'
  
import Nprogress from "./Nprogress";

export default {
  name: 'Layout',
  components:{
      Navbar,
      Footer,
      Nprogress
  }
}
  
</script>  

<style>
#nprogress>.bar{
  height:4px;
}
</style>
