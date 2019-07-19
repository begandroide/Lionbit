<template>
  <v-layout row justify-center>
    <v-dialog v-model="dialog" persistent max-width="600px">
      <template v-slot:activator="{ on }">
		<v-btn color="gray" round :disabled="(newStudent == null)" v-on="on">
			<i class="fa fa-edit"></i> Editar
		</v-btn>
      </template>
      <v-card v-if="newStudent != null">
        <v-card-title>
          <span class="headline">Editar estudiante</span>
        </v-card-title>
            <form ref="form" @submit.prevent="CreateStudent">
        <v-card-text>
          <v-container grid-list-md>
                <v-layout wrap>
                <v-flex xs12 sm6 md6>
                    <v-text-field label="Nombres*"
                        id="name-input"
                        v-model="newStudent.name"
                        :state="newStudent.name" required>
                    </v-text-field>
                </v-flex>
                <v-flex xs12 sm6 md6>
                    <v-text-field label="Apellidos*"
                        id="last-name-input"
						v-model="newStudent.last_name"
						:state="newStudent.last_name" hint="example of helper text only on focus"></v-text-field>
                </v-flex>
                <v-flex xs12 sm6 md6>
                    <v-text-field 
                        label="Rol Usm*" 
                        id="rol-input"
						v-model="newStudent.rol_usm"
						:state="newStudent.rol_usm" hint="example of helper text only on focus"></v-text-field>
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
import Students from './Students'
  const FormCreate =  {
      component:{
        Students,
      },
      props:{
		  students: Array,
			newStudent: Object, 
      },
    data: () => ({
      dialog: false,
    }),
    methods:{
      CreateStudent() {
        if(this.newStudent.last_name != null){
          api.updateStudentForId(this.newStudent).
            then( (response) => {  
              console.log(this.$root);
              this.students.push({  
                  id: response.data.id,  
                  name: this.newStudent.name,  
                  last_name: this.newStudent.last_name,
                  rol_usm:   this.newStudent.rol_usm
              })  
          }).catch((error) => {  
            this.$log.debug(error);  
              this.error = "Failed to add student"  
          }).finally( () => { 
            this.newStudent.id = 0;
            this.newStudent.name = "";
            this.newStudent.last_name = "";
            this.newStudent.rol_usm = "";
          });  
        // Hide the modal manually
        this.$nextTick(() => {
          this.dialog = false;
        });

        }
      },
    },
  }
  export default FormCreate;
</script>