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
					creditos_usm: this.newAssignature.creditos_usm,
				})  
		}).catch((error) => {  
			this.$log.debug(error);  
			this.error = "Failed to add";  
		});  
		this.dialog = false;
		 this.$notify({
                group: 'foo',
                title: 'Editado',
                text: 'Asignatura guardada satisfactoriamente!'
              });
		},
	},
	computed:{
	}
  }
  export default FormEdit;
</script>