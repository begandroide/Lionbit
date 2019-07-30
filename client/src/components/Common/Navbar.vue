<template>
	<span>
	<!-- Navigation drawer -->
	<v-navigation-drawer
      :mini-variant.sync="mini"
      :clipped="clipped"
      v-model="drawer"
		class="yellow darken-3" 
	  permanent
      app
    >
      <v-list>
        <template v-for="(item, i) in items">

          <template v-if="!item.child">
            <v-list-tile
              router exact
              :key="item.title"
              :to="item.to"
            >
              <v-list-tile-action>
				<v-icon  v-html="item.icon"></v-icon>
              </v-list-tile-action>
              <v-list-tile-content>
                <v-list-tile-title  v-text="item.title"></v-list-tile-title>
              </v-list-tile-content>
            </v-list-tile>
          </template>

          <template v-else>
            <v-list-group
              :key="i"
              :prepend-icon="item.icon"
              no-action
            >
              <v-list-tile slot="activator">
                <v-list-tile-content>
                  <v-list-tile-title  >{{ item.title }}</v-list-tile-title>
                </v-list-tile-content>
              </v-list-tile>
              <v-list-tile
                v-if="!mini"
                router exact
                v-for="subItem in item.child"
                :key="subItem.title"
                :to="subItem.to"
              >
                <v-list-tile-action>
                  <v-icon v-html="subItem.icon"></v-icon>
                </v-list-tile-action>
                <v-list-tile-content>
                  <v-list-tile-title  v-text="subItem.title"></v-list-tile-title>
                </v-list-tile-content>
              </v-list-tile>
            </v-list-group>
          </template>

        </template>
      </v-list>
    </v-navigation-drawer>
        <!-- Navbar principal -->
        <v-toolbar fixed :clipped-left="clipped" app color="blue darken-3" dark>
			<v-toolbar-side-icon class="md-and-up" @click="mini = !mini"></v-toolbar-side-icon>
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
import router from "../../router"
// app Vue instance


  const Navbar = {
	router: router,
    name: 'Navbar',
    components:{
            MenuProfile,
    },
        data: function(){
                return{
					clipped: true,
					fixed: false,
                        appTitle: "SIGA",
						drawer: false,
						mini: false,
                        items: [
								{ 	title: 'Inicio',
									icon: 'fa fa-home',
									to: '/',
									child: null },
								{ 	title: 'Administración',
									icon: 'fab fa-keycdn',
									child: 
									[ 	
										{title: 'Estudiantes',
										icon: 'fa fa-user-graduate',
										to: '/Students'  	},
										{ 	title: 'Profesores',
										icon: 'fa fa-chalkboard-teacher',
										to: '/Teachers'  	},
										{ 	title: 'Asignaturas',
										icon: 'fas fa-book',
										to: '/Assignatures'  	},
										{ 	title: 'Asignaturas asignadas',
										icon: 'fas fa-pen',
										to: '/TeacherAssignatures'  	}
										  ]
								}
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

