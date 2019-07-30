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
                <v-flex xs12 sm6 md6>
                    <v-text-field 
                        type = "number"
                        min = 1
                        max = 12
                        label="Semestre*"
                        id="Semestre-input"
                        hint="Semestre"
                        ></v-text-field>
                </v-flex>
                <v-flex xs12 sm6 md6>
                    <v-text-field 
                        type = "number"
                        min = 1
                        label="Cantidad alumnos*" 
                        id="cant-alumnos"
                        v-model="newAssignature.num_students"
                        :state="newAssignature.num_students" 
                        hint="Cantidad de alumnos totales para este curso">
                    </v-text-field>
                </v-flex>
                <v-flex xs12 sm6 md6>
                    <v-text-field
                        type = "number" 
                        min = 1
                        label="Cantidad paralelos*" 
                        id="num-paralelos"
						            v-model="newAssignature.num_paralelos"
						            :state="newAssignature.num_paralelos" 
                        hint="Cantidad de paralelos para el curso">
                    </v-text-field>
                </v-flex>
                </v-layout>
          </v-container>
          <small>*indica campos requeridos</small>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="error darken-1" flat @click="dialog = false">Close</v-btn>
          <v-btn color="success darken-1" type="submit" flat>Save</v-btn>
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
          num_students: null,
          num_paralelos: null,
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
                    num_students: this.newAssignature.num_students,
                    num_paralelos: this.newAssignature.num_paralelos,
                    creditos_usm: this.newAssignature.creditos_usm,
                })  
        }).catch((error) => {  
            this.$log.debug(error);  
            this.error = "Failed to add";  
        });  
        this.dialog = false;
        },
    },
    computed:{
    }
  }
  export default FormCreate;
</script>