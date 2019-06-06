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
                        v-model="newAssignature.last_name"
                        :state="newAssignature.last_name" hint="example of helper text only on focus"></v-text-field>
                </v-flex>
                <v-flex xs12 sm6 md6>
                    <v-text-field 
                        label="Cantidad alumnos*" 
                        id="cant-alumnos"
						v-model="newAssignature.cantAlumnos"
						:state="newAssignature.cantAlumnos" hint="example of helper text only on focus"></v-text-field>
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
import Assignatures from './Assignatures'
  const Form =  {
      component:{
        Assignatures,
      },
      props:{
          Assignatures: Array
      },
    data: () => ({
      dialog: false,
      newAssignature: {
        id: 0,
        name: null,
        sigla_usm: null,
        cantAlumnos: null,
        },
    }),
    methods:{
        handleSubmit2() {
            api.createNewAssignature(this.newAssignature, false).then( (response) => {  
                console.log(this.$root);
                this.assignatures.push({  
                    id: response.data.id,  
                    name: this.newAssignature.name,  
                    sigla_usm:   this.newAssignature.sigla_usm,
                    cantAlumnos: this.newAssignature.cantAlumnos
                })  
        }).catch((error) => {  
            this.$log.debug(error);  
                this.error = "Failed to add student"  
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