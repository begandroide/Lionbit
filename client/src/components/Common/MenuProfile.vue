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
              <img src="@/assets/user-profile.jpg">
            </v-list-tile-avatar>

            <v-list-tile-content>
              <!--<v-list-tile-title>{{activeUser.name}}</v-list-tile-title>-->
              <v-list-tile-title>Nombre Usuario</v-list-tile-title>
              <v-list-tile-sub-title>Acciones</v-list-tile-sub-title>
            </v-list-tile-content>

            <v-list-tile-action>
              <v-btn
                :class="fav ? '' : 'red--text'"
                icon
                @click="fav = !fav"
              >
                <v-icon>fas fa-cog</v-icon>
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
