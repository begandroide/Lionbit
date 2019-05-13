<template>
	<span>
	<!-- Navigation drawer -->
        <v-navigation-drawer app v-model="drawer" class="yellow darken-3" dark disable-resize-watcher>
			<v-list>
				<template v-for="(item, index) in items">
					<v-list-tile :key="index">
						<v-list-tile-content>
							<b-link :to="item.url">{{item.title}}</b-link>
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
			<b-nav-form class="my-2 my-lg-0">
				<b-form-input size="sm" placeholder="Search"></b-form-input>
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
									url: '/' },
                                { title: 'Estudiantes',
									url: '/Students'  	},
                                { title: 'Profesores',
									url: '/Teachers'  	},
                                { title: 'Asignaturas',
									url: '/'  	}
                        ],
                        claims: [],
                        currentRoute: window.location.pathname,
                        activeUser: null  
                };
        },
        // app initial state  
  
  async created () {  
        await this.refreshActiveUser()  
        this.claims = await this.$auth.getUser()
  },  
  
  watch: {  
    '$route': 'refreshActiveUser'  
  },  
  computed:{
  },  
   render (h) { return h(this.ViewComponent) },
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
  };

        export  default Navbar;
</script>

