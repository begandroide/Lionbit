<template>
  <v-layout row justify-center>
    <v-dialog v-model="dialog" persistent max-width="600px">
      <template v-slot:activator="{ on }">
        <v-btn color="primary" dark v-on="on">Añadir nuevo estudiante</v-btn>
      </template>
      <v-card>
        <v-card-title>
          <span class="headline">Nuevo estudiante</span>
        </v-card-title>
            <form ref="form" @submit.prevent="CreateStudent">
        <v-card-text>
          <v-container grid-list-md>
                <v-layout wrap>
                <v-flex xs12 sm6 md6>
                    <v-text-field label="Nombres*"
                        id="name-input"
                        v-model="newStudent.firstName"
                        :state="newStudent.firstName" required>
                    </v-text-field>
                </v-flex>
                <v-flex xs12 sm6 md6>
                    <v-text-field label="Apellidos*"
                        id="last-name-input"
						v-model="newStudent.lastName"
						:state="newStudent.lastName" hint="example of helper text only on focus"></v-text-field>
                </v-flex>
                <v-flex xs12 sm6 md6>
                    <v-text-field 
                        label="Rol Usm*" 
                        id="rol-input"
                        @change="checkError"
                        v-model="newStudent.rol_usm"
                        :error-messages="errorMessage" hint="example of helper text only on focus"></v-text-field>
                </v-flex>
                </v-layout>
          </v-container>
          <small>*indica campos requeridos</small>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="blue darken-1" flat @click="closeDialog">Cerrar</v-btn>
          <v-btn color="blue darken-1" type="submit" flat>Guardar</v-btn>
        </v-card-actions>
            </form>
      </v-card>
    </v-dialog>
  </v-layout>
</template>

<script>
import api from '../../Api';
import Students from './Students'
  const FormCreate =  {
      component:{
        Students,
      },
      props:{
          students: Array
      },
    data: () => ({
      errorMessage: "",
      error: false,
      dialog: false,
      newStudent: {
        id: 0,
        firstName: null,
        lastName: null,
        rol_usm: null,
        },
    }),
    methods:{
      checkError: function(value) {
        if(value === "" ){
          this.errorMessage = "";
          this.error = false; 
        }
      },

      CreateStudent() {
        if(this.newStudent.lastName != null){
          api.createNewStudent(this.newStudent).
            then( (response) => {  
              console.log(this.$root);
              this.students.push({  
                  id: response.data.id,  
                  firstName: this.newStudent.firstName,  
                  lastName: this.newStudent.lastName,
                  rol_usm:   this.newStudent.rol_usm
              })  
          }).catch((error) => {  
            this.$log.debug(error);  
              this.error = "Failed to add student";
              if(error.response.data.message.includes("Duplicate")){
                this.error = true;
              }
               
          }).finally( () => { 
            if(this.error){
              this.errorMessage = "Rol existente";
            } else{
              this.cleanNewStudent();
              // Hide the modal manually
              this.$nextTick(() => {
                this.dialog = false;
              });
            }
          }); 
        }
      },
      closeDialog() {
        this.dialog = false;
        this.cleanErrors();
        this.cleanNewStudent();
      },
      cleanNewStudent(){
        this.newStudent.id = 0;
        this.newStudent.firstName = null;
        this.newStudent.lastName = null;
        this.newStudent.rol_usm = null;
      },
      cleanErrors(){
        this.errorMessage = "";
        this.error = false;
      }
    },
  }
  export default FormCreate;
</script>