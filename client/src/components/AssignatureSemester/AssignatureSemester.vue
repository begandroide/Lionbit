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
									<i class="fas fa-book"></i>
									Asignaturas por semestre
								</h3>
								<p class="text-muted card-subtitle"> 
									Listado de asignaturas por semestre, con su respectiva cantidad de paralelos y alumnos. 
								</p>
							</div>

							<div class="col-md-4 col-sm-4">
								<div align="right">
									<FormCreate :assignatureSemesters="assignatureSemesters"/>
								</div >
							</div>

							<Actions :selected="selected" :assignatureSemesters="assignatureSemesters"/>
						</v-layout>
					</v-container>
					<!-- contenido asignaturas -->
					<v-container grid-list-md text-xs-center>
						<v-layout row wrap>
							<v-flex xs12 >
							<v-spacer></v-spacer>
							<v-text-field
								v-model="search"
								append-icon="search"
								label="Buscar"
								single-line
								hide-details
							></v-text-field>
							<v-data-table
								:headers="headers"
								:items="assignatureSemesters"
								class="elevation-1"
								:search="search"
								:aria-sort="true"
							>
								<template v-slot:items="props" >
									<tr  @click="showAlert(props.item)">
										<td class="hidden-id">{{props.item.id}}</td>
										<td class="text-xs-left">{{props.item.assignature.name}}</td>
										<td class="text-xs-center">{{ props.item.assignature.creditos_usm }}</td>
										<td class="text-xs-center">{{ props.item.numberParalelos }}</td>
										<td class="text-xs-center">{{ props.item.numberStudentsByParalelo }}</td>
										<td class="text-xs-left">{{ props.item.semester | filterSemester }}</td>
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
import FormCreate from "./FormCreate";
import Actions from "./Actions";
// app Vue instance
  const AssignatureSemester = {
		name: 'AssignatureSemester',
		components:{
			FormCreate,
			Actions
		},
        metaInfo: {
			title: 'Semestres',
			titleTemplate: '%s | Siga web App'
		},
		props: {
			activeUser: Object,
		},
		watch: {
			},
        data: function(){
			return{
				selected: null,
				search: '',
				headers: [
				{
					text: 'Id',
					value: 'id',
					class: 'hidden-id',
					sortable: true
				},
				{
					text: 'Asignatura',
					align: 'left',
					sortable: true,
					value: 'asignature'
				},
				{
					text: 'Créditos',
					align: 'right',
					sortable: true,
					value: 'creditos'
				},
				{ 	text: 'Paralelos',
					align: 'center',
					sortable: true,
					value: 'paralelos'
				},
				{ 	text: 'Numero de estudiantes por paralelo',
					align: 'center',
					sortable: true,
					value: 'studentNumberByParalelo'
				},
				{ 	text: 'Semestre',
					align: 'left',
					sortable: true,
					value: 'semester'
				},
				],
				loading: false,
				assignatureSemesters: [],
				message: null,
			};
        },
        created: function () {
			api.init();
			api.getAllAssignatureSemester()  
				.then(response => {  
				this.$log.debug("Data loaded: ", response.data); 
				this.assignatureSemesters = response.data;
				this.$log.debug("assignatures semesters : ", this.assignatureSemesters); 
				
				})  
				.catch(error => {  
				this.$log.debug(error)  
				this.error = "Failed to load assignatures semester"  
				})  
				.finally(() => this.loading = false)  
        },
        mounted() {
        },
        methods: {
			showAlert(a){
				if (event.target.classList.contains('btn__content')) return;
				if(this.selected === a ){
					this.selected = null;
					event.target.parentElement.classList.remove('selected-row-special');
				}else{
					if(this.selected != null){
						event.target.parentElement.parentElement.querySelector('.selected-row-special').classList.remove('selected-row-special');
					}
					event.target.parentElement.classList.add('selected-row-special');
					this.selected = a;
				}
			},
		},
		filters: {
			filterSemester(semester){
				return semester.yearSemester +'-'+ semester.numberSemester;
			}
		}
  }

        export  default AssignatureSemester;
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