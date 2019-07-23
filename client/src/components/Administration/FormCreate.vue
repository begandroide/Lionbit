<template>
  <v-layout row justify-center>
    <v-dialog v-model="dialog" persistent max-width="600px">
      <template v-slot:activator="{ on }">
        <v-btn color="primary" dark v-on="on">Añadir nuevo usuario</v-btn>
      </template>
      <v-card>
        <v-card-title>
          <span class="headline">Nuevo usuario</span>
        </v-card-title>
            <form ref="form" @submit.prevent="handleSubmit">
        <v-card-text>
          <v-container grid-list-md>
                <v-layout wrap>
                <v-flex xs12 sm6 md6>
                    <v-text-field label="Nombres*"
                        id="name-input"
                        v-model="newUser.firstName"
                        :state="newUser.firstName" required>
                    </v-text-field>
                </v-flex>
                <v-flex xs12 sm6 md6>
                    <v-text-field label="Apellidos*"
                        id="last-name-input"
						v-model="newUser.lastName"
						:state="newUser.lastName" hint="example of helper text only on focus"></v-text-field>
                </v-flex>
                <v-flex xs12 sm6 md6>
                    <v-text-field label="Oficina*"
                        id="oficina-input"
						v-model="newUser.office"
						:state="newUser.office" hint="example of helper text only on focus"></v-text-field>
                </v-flex>
                <v-flex xs12 sm6 md6>
                    <v-text-field label="Número teléfono*"
                        id="phoneNumber-input"
						v-model="newUser.phoneNumber"
						:state="newUser.phoneNumber" hint="example of helper text only on focus"></v-text-field>
                </v-flex>
                <v-flex xs12 sm6 md6>
                    <v-text-field 
                        label="Email*" 
                        id="rut-input"
						v-model="newUser.email"
						:state="newUser.email" hint="example of helper text only on focus"></v-text-field>
                </v-flex>
                <v-flex xs12 sm6 md6>
                    <v-text-field 
                        label="Contraseña*" 
                        id="rut-input"
						v-model="newUser.password"
						:state="newUser.password" hint="example of helper text only on focus"></v-text-field>
                </v-flex>
                </v-layout>
          </v-container>
          <small>*indica campos requeridos</small>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="blue darken-1" flat @click="dialog = false">Close</v-btn>
          <v-btn color="blue darken-1" type="submit" flat>Save</v-btn>
        </v-card-actions>
            </form>
      </v-card>
    </v-dialog>
  </v-layout>
</template>

<script>
import api from '../../Api';
import Users from './Users'
  const Form =  {
      component:{
        Users,
      },
      props:{
          users: Array
      },
    data: () => ({
      dialog: false,
      newUser: {
          activated: true,
          createAt: "",
          email: "",
          firstName: "",
          lastName: "",
          office: "",
          password: "",
          phoneNumber: "",
          roles: [
              {
              rolID: 1,
              role: "ADMIN"
              }
          ],
          userID: 0
        },
    }),
    methods:{
      handleSubmit() {
              /// postear un profesor
            // Exit when the form isn't valid
            // if (!this.checkFormValidity()) {
            // return
            this.$log.debug("New item created:", this.newUser) 
            api.createNewUser(this.newUser).then( (response) => {  
            this.$log.debug("New item created:", response);  
                    this.users.push(  
                    response.data)  
            }).catch((error) => {  
            this.$log.debug(error);  
                    this.error = "Failed to add teacher"  
                    });  
            // Hide the modal manually
            this.$nextTick(() => {
              this.dialog = false;
            })
    },
},
  }
  export default Form;
</script>