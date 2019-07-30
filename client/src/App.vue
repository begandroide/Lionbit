<template>
  <v-app>
    <v-content>
      <Nprogress />
      <router-view />  
    </v-content>
  </v-app>
</template>


<script>  
import Nprogress from "./views/Nprogress";

  // app Vue instance  
  const App = {
    name: 'App',  
    components: {
      Nprogress,
    },
    // app initial state  
    data: () => {  
      return {  
        currentRoute: window.location.pathname,
      }  
    },
    created: function () {
        if (!localStorage.token && this.$route.path !== '/') {
          this.$router.push('/?redirect=' + this.$route.path)
        }
    },
    methods:
    {
    }

}  
  
export default App  
  
</script>  