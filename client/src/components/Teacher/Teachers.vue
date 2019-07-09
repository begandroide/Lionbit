<template>
	<v-layout>
		<v-flex md9 offset-sm1 pt-5>
			<v-card>
				<v-card-title primary-title>
					<!-- Header/ Title -->
					<v-container >
						<v-layout row wrap>
							<div class="col-md-8 col-sm-8">
								<h3 class="card-title">
									<i class="fa fa-chalkboard-teacher"></i>
									Profesores
								</h3>
								<p class="text-muted card-subtitle"> Listado de profesores registrados en el sistema. </p>
							</div>

							<div class="col-md-4 col-sm-4">
								<div align="right">
									<FormCreate :teachers="teachers" />
								</div >
							</div>

							<Actions :selected="selected"/>
						</v-layout>
					</v-container>
					<!-- contenido estudiantes -->
					<v-container grid-list-xs text-xs-center>
						<!-- busqueda en tabla -->
						<v-text-field
							v-model="search"
							append-icon="search"
							label="Search"
							single-line
							hide-details
						></v-text-field>
						<!-- /busqueda en tabla -->
						<!-- Data table  -->
						<v-layout row wrap>
							<v-flex >
							<v-data-table
								:headers="headers"
								:items="teachers"
								class="elevation-1"
								:search="search"
								:aria-sort="true"
							>
								<template v-slot:items="props" >
									<tr @click="showAlert(props.item)">
										<td class="hidden-id">{{props.item.id}}</td>
										<td class="text-left">{{props.item.name}}</td>
										<td class="text-left">{{props.item.last_name}}</td>
										<td class="text-xs-left">{{ props.item.rut }}</td>	
									</tr>
								</template>

								<template v-slot:no-results>
									<v-alert :value="true" color="error" icon="warning">
									Tu búsqueda "{{ search }}" no tiene resultados.
									</v-alert>
								</template>

							</v-data-table>
							</v-flex>
						</v-layout>
					</v-container>
				</v-card-title>
			</v-card>
		</v-flex>
  </v-layout>
</template>


<script>
import api from '../../Api';
import FormCreate from './FormCreate';
import Actions from './Actions';

// app Vue instance
  const Teachers = {
	name: 'Teachers',
	components:{
		FormCreate,
		Actions
	},
	metaInfo: {
		title: 'Profesores',
		titleTemplate: '%s | Siga web App'
	},
    props: {
      activeUser: Object
    },
	data: function(){
		return{
			selected: null,
			teachers: [],
			delimiters: ['${ ', ' }'],
			loading: false,
			message: null,
			newTeacher: {
					name: null,
					last_name: null,
					rut: null
			},
			search: '',
			dialog: false,
			headers: [
			{
				text: 'Id',
				value: 'id',
				class: 'hidden-id',
			},
			{
				text: 'Nombres',
				align: 'left',
				sortable: true,
				value: 'name'
			},
			{
				text: 'Apellidos',
				align: 'left',
				sortable: true,
				value: 'last_name'
			},
			{ 	text: 'Rut',
				align: 'left',
				sortable: true,
				value: 'rut'
			}],
		};
	},
	created: function () {
	},
	mounted() {
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
			.finally(() => this.loading = false)  
        },
        methods: {
			showAlert(a){
				if (event.target.classList.contains('btn__content')) return;
				if(this.selected === a ){
					this.selected = null;
					event.target.parentElement.classList.remove('selected-row-special');
				}else{
					event.target.parentElement.classList.add('selected-row-special');
					this.selected = a;
				}
			},
        },
  }

        export  default Teachers
</script>

<style>
  [v-cloak] { display: none; }
  .hidden-id{
	  display: none;
  }
	.selected-row-special{
		background-color: #dbdbdb;
	}
</style>