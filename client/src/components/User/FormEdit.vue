<template>
  <v-layout row justify-center>
    <v-dialog v-model="dialog" persistent max-width="600px">
      <template v-slot:activator="{ on }">
		<v-btn color="gray" round :disabled="(newUser == null)" v-on="on">
			<i class="fa fa-edit"></i> Editar
		</v-btn>
      </template>
      <v-card v-if="newUser != null">
        <v-card-title>
          <span class="headline">Editar usuario</span>
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
                <v-flex xs12 sm6 md6>
                    <v-text-field label="Número de teléfono*"
                        id="last-name-input"
                        v-model="newUser.phoneNumber"
                        :state="newUser.phoneNumber" 
                        hint="Número"></v-text-field>
                </v-flex>
                <v-select
                  label="Roles"
                  :items="roles"
                  v-model="newUser.roles"
                  :multiple=true
                  item-value="rolID"
                  item-text="role"
                  ></v-select>
                </v-layout>
          </v-container>
          <small>*indica campos requeridos</small>
        </v-card-text>
              <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="blue darken-1" flat @click="dialog = false">Cerrar</v-btn>
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
  const FormCreate =  {
      component:{
        Users,
      },
      props:{
		  users: Array,
			newUser: Object, 
      },
    data: () => ({
      roles: [ {rolID:1 , role:"ADMIN"}, {rolID:2,role:"TEACHER"},{rolID:3,role:"DEPT_RESPONSABLE"}],
      dialog: false,
    }),
    methods:{
      handleSubmit() {
        // var rolSelected = this.roles.find(x => x.rolID === this.selectedRole);
        // this.newUser.roles = [{ "rolID": rolSelected.rolID , "role": rolSelected.role }]; 
        
              /// postear un profesor
            // Exit when the form isn't valid
            // if (!this.checkFormValidity()) {
            // return
        // api.updateTeacherForId(this.newTeacher, false).then( (response) => {  
        //         console.log(this.$root);
        //         this.teachers.update({  
        //           id: response.data.id,  
        //           name: this.newTeacher.name,  
        //           lastname:   this.newTeacher.lastname,
        //           rut: this.newTeacher.rut,
        //         })  
        //     }).catch((error) => {  
        //     this.$log.debug(error);  
        //             this.error = "Failed to add teacher"  
        //             });  
        //     // Hide the modal manually
        //     this.$nextTick(() => {
        //       this.dialog = false;
        //     })
    },
    },
  }
  export default FormCreate;
</script>