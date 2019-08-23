<template>
  <v-layout row justify-center>
    <v-dialog v-model="dialog" persistent max-width="600px">
      <template v-slot:activator="{ on }">
        <v-btn color="primary" dark v-on="on">Añadir nueva asignatura</v-btn>
      </template>
      <v-card>
        <v-card-title>
          <span class="headline">Nueva asignatura</span>
        </v-card-title>
            <form ref="form" @submit.prevent="handleSubmit2">
        <v-card-text>
          <v-container grid-list-md>
                <v-layout wrap>
                <v-flex xs12 sm6 md6>
                    <v-text-field label="Nombre*"
                        id="name-input"
                        v-model="newAssignature.name"
                        :state="newAssignature.name" required>
                    </v-text-field>
                </v-flex>
                <v-flex xs12 sm6 md6>
                    <v-text-field label="Siglas*"
                        id="last-input"
                        v-model="newAssignature.sigla"
                        :state="newAssignature.sigla" 
                        hint="Siglas descriptivas del curso"></v-text-field>
                </v-flex>
                <v-flex xs12 sm6 md6>
                    <v-text-field 
                        type = "number"
                        min = 1
                        max = 9
                        label="Créditos*"
                        id="creditos-input"
                        v-model="newAssignature.creditos_usm"
                        :state="newAssignature.creditos_usm" 
                        hint="Creditos usm"
                        ></v-text-field>
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
import Assignatures from './Assignatures'
  const FormCreate =  {
      components:{
        Assignatures,
      },
      props:{
        assignatures: Array
      },
      data: () => ({
        dialog: false,
        newAssignature: {
          id: 0,
          name: null,
          sigla: null,
          creditos_usm: 0,
        }
      }),
    methods:{
        handleSubmit2() {
            api.createNewAssignature(this.newAssignature, false).then( (response) => {  
                console.log(this.$root);
                this.assignatures.push({  
                    id: response.data.id,  
                    name: this.newAssignature.name,  
                    sigla:   this.newAssignature.sigla,
                    creditos_usm: this.newAssignature.creditos_usm,
                })  
        }).catch((error) => {  
            this.$log.debug(error);  
            this.error = "Failed to add";  
        }).finally( () => {

          if(this.error){
            this.errorMessage = "Asignatura ya registrada";
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
          this.newAssignature.id =  0;
          this.newAssignature.name = null;
          this.newAssignature.sigla = null;
          this.newAssignature.creditos_usm = null;
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