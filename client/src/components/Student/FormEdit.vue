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
						v-model="newStudent.rol_usm"
						:state="newStudent.rol_usm" hint="example of helper text only on focus"></v-text-field>
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
import Students from './Students'
  const FormEdit =  {
      component:{
        Students,
      },
      props:{
			  newStudent: Object, 
      },
    data: () => ({
      dialog: false,
    }),
    methods:{
      CreateStudent() {
        if(this.newStudent.lastName != null){
          api.updateStudentForId(this.newStudent).
            then( (response) => {  
              this.newStudent.studentID = response.data.studentID    
          }).catch((error) => {  
            this.$log.debug(error);  
              this.error = "Failed to add student"  
          }).finally( () => { 
            this.$log.debug("Creado satisfactoriamente")
          });  
        // Hide the modal manually
        this.$nextTick(() => {
          this.dialog = false;
        });

        }
      },
    },
  }
  export default FormEdit;
</script>