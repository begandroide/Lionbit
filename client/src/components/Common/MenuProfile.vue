<template>
  <div class="text-xs-center">
    <v-menu
      attach
      v-model="menu"
      :close-on-content-click="false"
      :nudge-width="200"
      left
      offset-y
    >
      <template v-slot:activator="{ on }">
            <v-btn color="transparent" dark v-on="on"><v-icon>fa fa-user</v-icon></v-btn>
      </template>

      <v-card>
        <v-list>
          <v-list-tile avatar>
            <v-list-tile-avatar>
              <img src="https://scontent.fscl11-2.fna.fbcdn.net/v/t1.0-1/p40x40/12924603_1095986657119471_2527884957920666729_n.jpg?_nc_cat=100&_nc_ht=scontent.fscl11-2.fna&oh=33c664260e5009c0d268a67375fe51a2&oe=5D6FE13B" alt="Benja">
            </v-list-tile-avatar>

            <v-list-tile-content>
              <v-list-tile-title>{{activeUser.name}}</v-list-tile-title>
              <v-list-tile-sub-title>Founder of Vuetify.js</v-list-tile-sub-title>
            </v-list-tile-content>

            <v-list-tile-action>
              <v-btn
                :class="fav ? 'red--text' : ''"
                icon
                @click="fav = !fav"
              >
                <v-icon>favorite</v-icon>
              </v-btn>
            </v-list-tile-action>
          </v-list-tile>
        </v-list>

        <v-divider></v-divider>

        <v-btn @click="handleLogout()" justify-center color="error"  large dark>
          Cerrar sesión
        </v-btn>

      </v-card>
    </v-menu>
  </div>
</template>

<script>
import {TokenService} from "../../services/storage.service"

  const MenuProfile = {
    name: 'MenuProfile',
    data: function() {
      return {
        activeUser: [],
        fav: true,
        menu: false,
        message: false,
        hints: true
    };},
     async created() {
        this.activeUser = await localStorage.token;
      },
      methods:{
      async handleLogout () {  
        TokenService.removeToken();  
        this.$router.go('/login')  
      }  
      }
  }
  export default MenuProfile; 
</script>