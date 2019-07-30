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
        <!-- <v-navigation-drawer 
			v-model="drawer" 
 			:clipped="true"
			class="yellow darken-3" 
			:mini-variant.sync="mini"
        	permanent
			mini-variant-width=80
			app 
			>
			<v-list>
				<template v-for="(item, index) in items">

					<v-list-group no-action
						v-if="item.child != null" :key="index">
						<template v-slot:activator>
							<v-list-tile class="ma-0 pa-0" >
								<v-list-tile-action >
									<v-icon color="white">{{ item.icon }}</v-icon>
								</v-list-tile-action>

								<v-list-tile-content >
									<b-link :to="item.url" class="font-weight-bolder text-white">
										{{item.title}}
									</b-link>
								</v-list-tile-content>
							</v-list-tile>
						</template>
						<div  v-if="!mini">
						<v-list-tile v-for="(item2,index2) in item.child"
									:key="index2" sub-group>

									<v-list-tile-action class="ma-0 pa-0">
										<v-icon color="white">{{ item2.icon }}</v-icon>
									</v-list-tile-action>

									<v-list-tile-content class="pa-0 ma-0">
										<b-link :to="item2.url" class="font-weight-bolder text-white">
											{{item2.title}}
										</b-link>
									</v-list-tile-content>

								</v-list-tile>
						</div>	
					</v-list-group>

					<v-list-tile  v-else
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
				</template>
			</v-list>
       </v-navigation-drawer>
 -->
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
										to: '/Assignatures'  	}
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

