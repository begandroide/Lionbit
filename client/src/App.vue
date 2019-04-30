<template>
  <div id="app">
    <Students />
    <Teachers />
    <router-view :activeUser="activeUser"/>  
    <footer class="info">  
      <p v-if="activeUser" class="logout-link"><a @click="handleLogout" href="#">Logout</a></p>  
      <p>LionBit TEAM - SGA</p>  
    </footer> 
  </div>
</template>

<!--<script>
  import Students from './components/Students'
  import Teachers from './components/Teachers'
// app Vue instance
  const app = {
    name: 'app',
    components: {
      Students,
      Teachers
    },
    // app initial state
    data: () => {
      return {
      }
    }
  }

  export default app
</script>-->

<script>  
  // app Vue instance  
  import Students from './components/Students'
  import Teachers from './components/Teachers'
  const app = {
    name: 'app',  
    components: {
      Students,
      Teachers
    },
    // app initial state  
    data: () => {  
      return {  
        activeUser: null  
      }  
    },  
  
  async created () {  
    await this.refreshActiveUser()  
  },  
  
  watch: {  
    '$route': 'refreshActiveUser'  
  },  
  
  methods: {  
    async refreshActiveUser () {  
      this.activeUser = await this.$auth.getUser()  
      this.$log.debug('activeUser',this.activeUser)  
    },  
  
    async handleLogout () {  
      await this.$auth.logout()  
      await this.refreshActiveUser()  
      this.$router.go('/')  
    }  
  },
}  
  
export default app  
  
</script>  

<style>
  [v-cloak] { display: none; }
</style>