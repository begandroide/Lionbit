<template>
  <v-layout row justify-center>
    <v-dialog v-model="dialog" persistent max-width="600px">
      <template v-slot:activator="{ on }">
        <v-btn color="primary" dark v-on="on">Asignar asignatura</v-btn>
      </template>
      <v-card>
        <v-card-title>
          <span class="headline">Asignar un profesor a asignatura</span>
        </v-card-title>
            <form ref="form" @submit.prevent="handleSubmit">
        <v-card-text>
          <v-container grid-list-md>
                <v-layout wrap>
                  <v-flex xs12 sm6 d-flex>
                    <v-select
                      :items="teachers"
                      item-text="name"
                      item-value="id"
                      label="Profesor"
                      v-model="newTeacherAssignature.teacherId"
                      required
                    ></v-select>
                  </v-flex>
                  <v-flex xs12 sm6 d-flex>
                    <v-select
                      :items="assignatures"
                      item-text="name"
                      item-value="id"
                      label="Asignatura"
                      v-model="newTeacherAssignature.assignatureId"
                      required
                    ></v-select>
                  </v-flex>
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
import TeacherAssignature from "./TeacherAssignature.vue";

const Form =  {
      component:{
      },
      props:{
          teacherAssignature: Array
      },
    data: () => ({
      dialog: false,
      newTeacherAssignature: {
        id: 0,
        assignatureId: 0,
        teacherId: 0,
        semesterTaught: "FIRST"
      },
      teachers: [],
      assignatures: [],
    }),
    mounted(){
      },
    created: function () {
		  api.init();
      api.getAllTeachers()  
        .then(response => {  
          this.$log.debug("Data loaded: ", response.data.content); 
          this.teachers = response.data.content;
          this.$log.debug("teachers : ", this.teachers); 
                          
        })  
        .catch(error => {  
          this.$log.debug(error)  
          this.error = "Failed to load teachers"  
          })  
        .finally(() => this.loading = false);  
        
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
    },
    
    methods:{
      handleSubmit() {
        // if (!this.checkFormValidity()) {
        // returnt
        this.$log.debug("New item created:", this. newTeacherAssignature) 
        api.createNewTeacherAssignature(this.newTeacherAssignature).then( (response) => {  
          this.$log.debug("New item created:", response);  
          this.teachersAssignature.push({  
            id: response.data.id,  
            assignature_id: this.newTeacherAssignature.assignature_id,  
            teacher_id: this.newTeacherAssignature.teacher_id,
          });  
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