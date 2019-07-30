<template>
  <v-layout >
	<v-dialog v-model="dialog" persistent max-width="600px">
	  <template v-slot:activator="{ on }">
		<v-btn color="gray" :round=true :disabled="(newAssignature == null)" v-on="on">
			<i class="fa fa-edit"></i> Editar
		</v-btn>
	  </template>
	  <v-card v-if="newAssignature != null">
		<v-card-title>
		  <span class="headline">Editar asignatura</span>
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
import Assignatures from './Assignatures'
  const FormEdit =  {
	  components:{
		Assignatures,
	  },
	  props:{
		assignatures: Array,
		newAssignature: Object
	  },
	  data: () => ({
		dialog: false,
	}),
	methods:{
		handleSubmit2() {
			api.updateAssignatureForId(this.newAssignature.id,this.newAssignature, false).then( (response) => {  
				console.log(this.$root);
				this.Assignatures.update({  
					id: response.data.id,  
					name: this.newAssignature.name,  
					sigla:   this.newAssignature.sigla,
					num_students: this.newAssignature.num_students,
					num_paralelos: this.newAssignature.num_paralelos
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
  export default FormEdit;
</script>