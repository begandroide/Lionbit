<template>
  <v-layout row justify-center>
    <v-dialog v-model="dialog" persistent max-width="600px">
      <template v-slot:activator="{ on }">
		<v-btn color="gray" round :disabled="(newTeacher == null)" v-on="on">
			<i class="fa fa-edit"></i> Editar
		</v-btn>
      </template>
      <v-card v-if="newTeacher != null">
        <v-card-title>
          <span class="headline">Editar profesor</span>
        </v-card-title>
            <form ref="form" @submit.prevent="handleSubmit">
        <v-card-text>
          <v-container grid-list-md>
                <v-layout wrap>
                <v-flex xs12 sm6 md6>
                    <v-text-field label="Nombres*"
                        id="name-input"
                        v-model="newTeacher.name"
                        :state="newTeacher.name" required>
                    </v-text-field>
                </v-flex>
                <v-flex xs12 sm6 md6>
                    <v-text-field label="Apellidos*"
                        id="last-name-input"
						v-model="newTeacher.lastname"
						:state="newTeacher.lastname" hint="example of helper text only on focus"></v-text-field>
                </v-flex>
                <v-flex xs12 sm6 md6>
                    <v-text-field 
                        label="Rut*" 
                        id="rut-input"
						v-model="newTeacher.rut"
						:state="newTeacher.rut" hint="example of helper text only on focus"></v-text-field>
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
  const FormCreate =  {
      component:{
      },
      props:{
		  teachers: Array,
			newTeacher: Object, 
      },
    data: () => ({
      dialog: false,
    }),
    methods:{
      handleSubmit() {
              /// postear un profesor
            // Exit when the form isn't valid
            // if (!this.checkFormValidity()) {
            // return
        api.updateTeacherForId(this.newTeacher, false).then( (response) => {  
                console.log(this.$root);
                this.teachers.update({  
                  id: response.data.id,  
                  name: this.newTeacher.name,  
                  lastname:   this.newTeacher.lastname,
                  rut: this.newTeacher.rut,
                })  
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
  export default FormCreate;
</script>