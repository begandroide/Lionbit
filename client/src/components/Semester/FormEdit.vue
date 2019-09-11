<template>
  <v-layout >
	<v-dialog v-model="dialog" persistent max-width="600px">
	  <template v-slot:activator="{ on }">
		<v-btn color="gray" :round=true :disabled="(newSemester == null)" v-on:click="showModal()" >
			<i class="fa fa-edit"></i> Editar
		</v-btn>
	  </template>
	  <v-card v-if="newSemesterCpy != null">
		<v-card-title>
		  <span class="headline">Editar semestre</span>
		</v-card-title>
			<form ref="form" @submit.prevent="handleSubmit2">
		<v-card-text>
		  <v-container grid-list-md :v-if="newSemesterCpy !== null">
				<v-layout wrap>
				<v-flex xs12 sm6 md6>
					<v-text-field label="Número de semestre*"
						id="name-input"
						v-model="newSemesterCpy.numberSemester"
						:state="newSemesterCpy.numberSemester" required>
					</v-text-field>
				</v-flex>
				<v-flex xs12 sm6 md6>
					<v-text-field label="Año*"
						id="last-input"
						v-model="newSemesterCpy.yearSemester"
						:state="newSemesterCpy.yearSemester" 
						hint="Año del semestre a iniciar"></v-text-field>
				</v-flex>
				</v-layout>
		  </v-container>
		  <small>*indica campos requeridos</small>
		</v-card-text>
		<v-card-actions>
		  <v-spacer></v-spacer>
		  <v-btn color="error darken-1" flat @click="dialog = false">Cerrar</v-btn>
		  <v-btn color="success darken-1" type="submit" flat>Guardar</v-btn>
		</v-card-actions>
			</form>
	  </v-card>
	</v-dialog>
  </v-layout>
</template>

<script>
import api from '../../Api';
import Semester from './Semester';

  const FormEdit =  {
	  components:{
		Semester,
	  },
	  props:{
		semesters: Array,
		newSemester: Object
	  },
	  data: () => ({
		dialog: false,
		newSemesterCpy : null,
	}),
	methods:{
		showModal(){
			this.dialog = true;
			this.newSemesterCpy = this.$_.cloneDeep(this.newSemester);
			console.log(this.newSemesterCpy);
		},
		handleSubmit2() {
			api.updateSemesterForId(this.newSemesterCpy.id,this.newSemesterCpy, false).then( (response) => {  
				console.log(this);
				this.newSemester.yearSemester = this.newSemesterCpy.yearSemester;
				this.newSemester.numberSemester = this.newSemesterCpy.numberSemester;
				this.dialog = false;
				this.$notify({
					group: 'foo',
					title: 'Editado',
					text: 'Semestre guardado exitosamente!'
              	});
		}).catch((error) => {  
			this.$log.debug(error);  
			this.error = "Failed to add";  
		});  
		},
	},
	computed:{
	}
  }
  export default FormEdit;
</script>