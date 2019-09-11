<template>
  <v-app>
    <v-content>
      <router-view />  
      <notifications position="bottom right" group="foo" />
    </v-content>
  </v-app>
</template>


<script>  
  // app Vue instance  
  const App = {
    name: 'App', 
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