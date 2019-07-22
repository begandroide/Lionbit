<template>
  <v-app>
    <v-content>
      <router-view />  
    </v-content>
  </v-app>
</template>


<script>  

  // app Vue instance  
  const App = {
    name: 'App',  
    components: {
    },
    // app initial state  
    data: () => {  
      return {  
        currentRoute: window.location.pathname,
      }  
    },
    methods:
    {
      updated () {
        if (!localStorage.token && this.$route.path !== '/') {
          this.$router.push('/?redirect=' + this.$route.path)
        }
      }
    }

}  
  
export default App  
  
</script>  