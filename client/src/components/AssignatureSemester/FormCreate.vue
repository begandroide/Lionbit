<template>
  <v-layout row justify-center>
    <v-dialog v-model="dialog" persistent max-width="600px">
      <template v-slot:activator="{ on }">
        <v-btn color="pink lighten-1" dark v-on="on"><i class="fa fa-add"></i>Configurar asignatura en semestre</v-btn>
      </template>
      <v-card>
        <v-card-title>
          <span class="headline">Nueva configuración de asignatura semestre</span>
        </v-card-title>
            <form ref="form" @submit.prevent="handleSubmit2">
        <v-card-text>
          <v-container grid-list-md>
               <v-layout wrap>
                <v-flex xs12 sm6 md6>
                   <v-select
                      :items="assignatures"
                      label="Asignatura"
                      item-text="name"
                      item-value="id"
                      v-model="newAssignatureSemester.assignatureId"
                      required
                    ></v-select>
                </v-flex>
                <v-flex xs12 sm6 md6>
                   <v-select
                      :items="semesters"
                      :item-text="(v) => v.yearSemester +'-'+ v.numberSemester"
                      item-value="id"
                      label="Número de semestre"
                      v-model="newAssignatureSemester.semesterId"
                      @change="checkError"
                      :error-messages="errorMessage"
                      required
                    ></v-select>
                </v-flex>
                 <v-flex xs12 sm6 md6>
                    <v-text-field label="Paralelos*"
                        id="last-name-input"
                        v-model="newAssignatureSemester.numberParalelos"
                        hint="Cantidad de paralelos a impartir de la asignatura"></v-text-field>
                </v-flex>
                 <v-flex xs12 sm6 md6>
                    <v-text-field label="Número de alumnos*"
                        id="last-name-input"
                        v-model="newAssignatureSemester.numberStudentsByParalelo"
                        hint="Número de alumnos por paralelo"></v-text-field>
                </v-flex>
                </v-layout>
          </v-container>
          <small>*indica campos requeridos</small>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="error darken-1" flat @click="closeDialog">Cerrar</v-btn>
          <v-btn color="success darken-1" type="submit" flat>Guardar</v-btn>
        </v-card-actions>
            </form>
      </v-card>
    </v-dialog>
  </v-layout>
</template>

<script>
import api from '../../Api';
import AssignatureSemester from './AssignatureSemester'

  const FormCreate =  {
      components:{
        AssignatureSemester,
      },
      props:{
        assignatureSemesters: Array
      },
      data: () => ({
        errorMessage: "",
        error: false,
        dialog: false,
        newAssignatureSemester: {
          id: 0,
          assignatureId: null,
          numberParalelos: 0,
          numberStudentsByParalelo: 0,
          semesterId: null
        },
        assignatures: null,
        semesters: null,
        loading: false,
      }),
      created:function(){
         api.getAllAssignatures()  
          .then(response => {  
            this.$log.debug("Data loaded: ", response.data.content); 
            this.assignatures = response.data.content;
            this.$log.debug("assignatures : ", this.assignatures); 
                            
          })  
          .catch(error => {  
            this.$log.debug(error)  
            this.error = "Failed to load assignatures"  
            })  
          .finally(() => this.loading = false);
          api.getAllSemesters()  
            .then(response => {  
              this.$log.debug("Data loaded: ", response.data); 
              this.semesters = response.data.content;
              this.$log.debug("semestre : ", this.semesters); 
                              
            })  
            .catch(error => {  
              this.$log.debug(error)  
              this.error = "Failed to load semesters"  
              })  
            .finally(() => this.loading = false);
      },
    methods:{
      checkError: function(value) {
        if(value === "" ){
          this.errorMessage = "";
          this.error = false; 
        }
      },
        handleSubmit2() {

            api.createNewAssignatureSemester(this.newAssignatureSemester).then( (response) => {  
                console.log(this.$root);
                this.assignatureSemesters.push({  
                    id: response.data.id,  
                    numberParalelos: this.newAssignatureSemester.numberParalelos,  
                    numberStudentsByParalelo:   this.newAssignatureSemester.numberStudentsByParalelo,
                    semesterId: this.newAssignatureSemester.semesterId,
                    assignatureId: this.newAssignatureSemester.assignatureId
                })  
        }).catch((error) => {  
            this.$log.debug(error);  
            this.error = "Failed to add";  
            if(error.response.data.message.includes("Duplicate")){
                this.error = true;
            }
        }).finally( () => {

          if(this.error){
            this.errorMessage = "Semestre ya registrado";
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
          this.newAssignatureSemester.id =  0;
          this.newAssignatureSemester.assignatureId = null;
          this.newAssignatureSemester.numberParalelos = 0;
          this.newAssignatureSemester.numberStudentsByParalelo = 0;
          this.newAssignatureSemester.semesterId = null;
      },
      cleanErrors(){
        this.errorMessage = "";
        this.error = false;
      }
    },
    computed:{
    }
  }
  export default FormCreate;
</script>