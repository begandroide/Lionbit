<template>
	<span>
	<!-- Navigation drawer -->
        <v-navigation-drawer 
			app 
			v-model="drawer" 
			class="yellow darken-3" 
			disable-resize-watcher
			>
			<v-list>
				<template v-for="(item, index) in items">
					<v-list-tile  
						 :key="index">

						 <v-list-tile-action class="ma-0 pa-0">
							<v-icon color="white">{{ item.icon }}</v-icon>
						</v-list-tile-action>

						<v-list-tile-content class="pa-0 ma-0">
							<b-link :to="item.url" class="font-weight-bolder text-white">
								{{item.title}}
							</b-link>
						</v-list-tile-content>

					</v-list-tile>
					<v-divider :key="`divider-${index}`"></v-divider>
				</template>
			</v-list>
       </v-navigation-drawer>

        <!-- Navbar principal -->
        <v-toolbar app color="blue darken-3" dark>
			<v-toolbar-side-icon class="md-and-up" @click="drawer = !drawer"></v-toolbar-side-icon>
			<v-toolbar-title>{{appTitle}}</v-toolbar-title>
			
			<v-spacer ></v-spacer>
			<v-layout row wrap>
					<b-nav-form class="col-md-10 col-sm-10">
						<b-form-input size="sm" placeholder="Search" style="width:80%;"></b-form-input>
							<v-btn
								flat
								class="my-2 my-sm-0"
								type="submit"
								dark
								icon
							>
								<v-icon >fa fa-search</v-icon>
							</v-btn>
					</b-nav-form>
			</v-layout>
			
			<v-spacer ></v-spacer>
			<MenuProfile v-if="activeUser"></MenuProfile>
				
        </v-toolbar>
       </span>
</template>

<script>
import MenuProfile from './MenuProfile.vue'
// app Vue instance

  const Navbar = {
    name: 'Navbar',
    components:{
            MenuProfile,
    },
        data: function(){
                return{
                        appTitle: "SIGA",
                        drawer: false,
                        items: [
								{ 	title: 'Inicio',
									icon: 'fa fa-home',
									url: '/' },
								{ 	title: 'Estudiantes',
									icon: 'fa fa-user-graduate',
									url: '/Students'  	},
								{ 	title: 'Profesores',
									icon: 'fa fa-chalkboard-teacher',
									url: '/Teachers'  	},
								{ 	title: 'Asignaturas',
									icon: 'fas fa-book',
									url: '/Assignatures'  	},
								{ 	title: 'Administración',
								icon: 'fab fa-keycdn',
								url: '/admin'  	}
                        ],
                        claims: [],
                        currentRoute: window.location.pathname,
                        activeUser: null  
                };
        },
        // app initial state  
  
  async created () {  
        await this.refreshActiveUser()  
        this.claims = await localStorage.token
  },  
  
  watch: {  
    '$route': 'refreshActiveUser'  
  },  
  computed:{
  },  
   render (h) { return h(this.ViewComponent) },
  methods: {  
    async refreshActiveUser () {  
      this.activeUser = await await localStorage.token  
      this.$log.debug('activeUser',this.activeUser)  
    },  
  
    async handleLogout () {  
      await this.$auth.logout()  
      await this.refreshActiveUser()  
      this.$router.go('/')  
    }  
  },
  };

        export  default Navbar;
</script>

<style>
 
</style>

