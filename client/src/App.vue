<template>
  <v-app>
    <Navbar />
    <v-content>
      <router-view />  
    </v-content>
    <Footer />
  </v-app>
</template>


<script>  
import Footer from './components/Common/Footer.vue'
import Navbar from './components/Common/Navbar.vue'

  // app Vue instance  
  const App = {
    name: 'App',  
    components: {
      Navbar,
      Footer,
    },
    // app initial state  
    data: () => {  
      return {  
        currentRoute: window.location.pathname,
      }  
    },

}  
  
export default App  
  
</script>  