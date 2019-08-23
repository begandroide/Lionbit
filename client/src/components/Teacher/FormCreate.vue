<template>
  <v-layout row justify-center>
    <v-dialog v-model="dialog" persistent max-width="600px">
      <template v-slot:activator="{ on }">
        <v-btn color="primary" dark v-on="on">Añadir nuevo profesor</v-btn>
      </template>
      <v-card>
        <v-card-title>
          <span class="headline">Nuevo profesor</span>
        </v-card-title>
            <form ref="form" @submit.prevent="handleSubmit">
        <v-card-text>
          <v-container grid-list-md>
                <v-layout wrap>
                <v-flex xs12 sm6 md6>
                  <v-text-field label="Nombres*"
                      id="name-input"
                      v-model="newTeacher.name"
                      :state="newTeacher.name" 
                      hint="Nombres del profesor"
                      required>
                  </v-text-field>
                </v-flex>
                <v-flex xs12 sm6 md6>
                    <v-text-field label="Apellidos*"
                        id="last-name-input"
                        v-model="newTeacher.lastname"
                        :state="newTeacher.lastname" 
                        hint="Apellidos del profesor"></v-text-field>
                </v-flex>
                <v-flex xs12 sm6 md6>
                    <v-text-field 
                        label="Rut*" 
                        id="rut-input"
						            v-model="newTeacher.rut"
                        :error-messages="errorMessage" 
                        hint="Rut del profesor"></v-text-field>
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
import Teachers from './Teachers'
const Form =  {
      component:{
        Teachers,
      },
      props:{
          teachers: Array
      },
    data: () => ({
      dialog: false,
      newTeacher: {
        id: 0,
        name: '',
        lastname: '',
        rut: '',
        },
    }),
    methods:{
      handleSubmit() {
        // if (!this.checkFormValidity()) {
        // return
        this.$log.debug("New item created:", this.newTeacher) 
        api.createNewTeacher(this.newTeacher).then( (response) => {  
          this.$log.debug("New item created:", response);  
          this.teachers.push({  
            id: response.data.id,  
            name: this.newTeacher.name,  
            lastname: this.newTeacher.lastname,
            rut:   this.newTeacher.rut 
          });  
        }).catch((error) => {  
          this.$log.debug(error);  
          this.error = "Failed to add teacher"  
          if(error.response.data.message.includes("Duplicate")){
              this.error = true;
          }
        }).finally( () => {

          if(this.error){
            this.errorMessage = "Profesor ya registrado";
            } else{
              this.closeDialog();
            }
          }
        );
    },
    closeDialog() {
      this.dialog = false;
      this.cleanErrors();
      this.cleanNewSemester();
    },
    cleanNewSemester(){
        this.newTeacher.id =  0;
        this.newTeacher.name = null;
        this.newTeacher.lastname = null;
        this.newTeacher.rut = null;
    },
    cleanErrors(){
      this.errorMessage = "";
      this.error = false;
    }
},
  }
  export default Form;
</script>