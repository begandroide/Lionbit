<template>
  <v-layout row justify-center>
    <v-dialog v-model="dialog" persistent max-width="600px">
      <template v-slot:activator="{ on }">
        <v-btn color="primary" dark v-on="on">Añadir nuevo semestre</v-btn>
      </template>
      <v-card>
        <v-card-title>
          <span class="headline">Nuevo semestre</span>
        </v-card-title>
            <form ref="form" @submit.prevent="handleSubmit2">
        <v-card-text>
          <v-container grid-list-md>
               <v-layout wrap>
                <v-flex xs12 sm6 md6>
                   <v-select
                      :items="years"
                      label="Año"
                      v-model="newSemester.yearSemester"
                      required
                    ></v-select>
                </v-flex>
                <v-flex xs12 sm6 md6>
                   <v-select
                      :items="semester"
                      label="Número de semestre"
                      v-model="newSemester.numberSemester"
                      @change="checkError"
                      :error-messages="errorMessage"
                      required
                    ></v-select>
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
import Semester from './Semester'

  const FormCreate =  {
      components:{
        Semester,
      },
      props:{
        semesters: Array
      },
      data: () => ({
        errorMessage: "",
        error: false,
        dialog: false,
        newSemester: {
          semesterID: 0,
          numberSemester: null,
          yearSemester: null,
          inCourse: false,
          active: true
        },
        years:["2018","2019","2020","2021","2022"],
        semester:["1","2","3"]
      }),
    methods:{
      checkError: function(value) {
        if(value === "" ){
          this.errorMessage = "";
          this.error = false; 
        }
      },
        handleSubmit2() {
            api.createNewSemester(this.newSemester).then( (response) => {  
                console.log(this.$root);
                this.semesters.push({  
                    id: response.data.semesterID,  
                    numberSemester: this.newSemester.numberSemester,  
                    yearSemester:   this.newSemester.yearSemester,
                    inCourse: this.newSemester.inCourse,
                    active: this.newSemester.active
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
            this.$notify({
                group: 'foo',
                title: 'Semestre registrado',
                text: 'Favor ingresa otro!'
              });
            } else{
              this.closeDialog();
              this.$notify({
                group: 'foo',
                title: 'Creado exitoso',
                text: 'Nuevo semestre añadido!'
              });
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
        this.newSemester.id = 0;
        this.newSemester.yearSemester = null;
        this.newSemester.numberSemester = null;
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