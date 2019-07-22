<template>
  <v-app>
    <Navbar />
      <router-view />  
    <Footer />
  </v-app>
</template>

<script>  
import Footer from '../components/Common/Footer.vue'
import Navbar from '../components/Common/Navbar.vue'
  
export default {
  name: 'Layout',
  components:{
      Navbar,
      Footer
  }
}
  
</script>  
