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
                      :state="newUser.firstName" 
                      hint="Nombres del usuario"
                      required>
                  </v-text-field>
                </v-flex>
                <v-flex xs12 sm6 md6>
                    <v-text-field label="Apellidos*"
                        id="last-name-input"
                        v-model="newUser.lastName"
                        :state="newUser.lastName" 
                        hint="Apellidos del usuario"></v-text-field>
                </v-flex>
                <v-flex xs12 sm6 md6>
                    <v-text-field label="Correo*"
                        id="last-name-input"
                        v-model="newUser.email"
                        :state="newUser.email" 
                        :rules=" [ v => /.+@.+\..+/.test(v)  || ( v.length == 0  || 'Correo inválido') ]"
                        hint="Correo del usuario"></v-text-field>
                </v-flex>
                <v-flex xs12 sm6 md6>
                    <v-text-field label="Contraseña*"
                        id="last-name-input"
                        type="password"
                        v-model="newUser.password"
                        :state="newUser.password" 
                        hint="Correo del usuario"></v-text-field>
                </v-flex>
                <v-flex xs12 sm6 md6>
                    <v-text-field label="Oficina*"
                        id="last-name-input"
                        v-model="newUser.office"
                        :state="newUser.office" 
                        hint="Oficina del usuario"></v-text-field>
                </v-flex>
                <v-select
                  label="Roles"
                  :items="roles"
                  :multiple=true
                  v-model="selectedRole"
                  item-value="rolID"
                  item-text="role"
                  ></v-select>
                </v-layout>
          </v-container>
          <small>*indica campos requeridos</small>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="blue darken-1" flat @click="closeDialog()">Cerrar</v-btn>
          <v-btn color="blue darken-1" type="submit" flat>Guardar</v-btn>
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
      roles: [ {rolID:1 , role:"ADMIN"}, {rolID:2,role:"TEACHER"},{rolID:3,role:"DEPT_RESPONSABLE"}],
      selectedRole: null,
      dialog: false,
      error: false,
      errorMessage: "",
      newUser: {
				activated: true,
				email: "",
				firstName: "",
				lastName: "",
				office: "",
				password: "",
				phoneNumber: "",
				roles: [
				{
					rolID: null,
					role: ""
					}
				],
				userID: 0
			},
    }),
    methods:{
      handleSubmit() {
        // if (!this.checkFormValidity()) {
        // return
        this.$log.debug("New item created:", this.newUser);
        this.newUser.roles = [];
        this.selectedRole.forEach(id => {
          var rolSelected = this.roles.find(x => x.rolID === id);
          this.newUser.roles.push(
            { "rolID": rolSelected.rolID , "role": rolSelected.role }
          ); 
        });
        
        this.$log.debug("role ", this.newUser.role ) 
        api.createNewUser(this.newUser).then( (response) => {  
          this.$log.debug("New item created:", response);  
          this.teachers.push({  
            userID: response.data.id,  
            name: this.newUser.firstName,  
            rut:   this.newUser.rut 
          });  
        }).catch((error) => {  
          this.$log.debug(error);  
          this.error = "Failed to add teacher"  
        }).finally(()=>{
          this.closeDialog();
        });  
        
    },
    closeDialog() {
      this.dialog = false;
      this.cleanErrors();
      this.cleanNewSemester();
    },
    cleanNewSemester(){
        this.newUser.id =  0;
        this.newUser.name = null;
        this.newUser.lastname = null;
        this.newUser.email = null;
        this.newUser.password = null;
        this.newUser.office = null;
        this.newUser.role = {};
    },
    cleanErrors(){
      this.errorMessage = "";
      this.error = false;
    }
},
  }
  export default Form;
</script>