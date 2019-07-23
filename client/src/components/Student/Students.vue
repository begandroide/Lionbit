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
									<i class="fa fa-user-graduate"></i>
									Estudiantes
								</h3>
								<p class="text-muted card-subtitle"> Listado de estudiantes registrados en el sistema. </p>
							</div>

							<div class="col-md-4 col-sm-4">
								<div align="right">
									<FormCreate :students="students" />
								</div >
							</div>

							<Actions :selected="selected"/>
						</v-layout>
					</v-container>
					<!-- contenido estudiantes -->
					<v-container grid-list-md text-xs-center>
						<v-layout row wrap>
							<v-flex xs12 >
							<v-spacer></v-spacer>
							<v-text-field
								v-model="search"
								append-icon="search"
								label="Search"
								single-line
								hide-details
							></v-text-field>
							<v-data-table
								:headers="headers"
								:items="students"
								class="elevation-1"
								:search="search"
								:aria-sort="true"
							>
								<template v-slot:items="props">
									<tr  @click="showAlert(props.item)">
										<td class="hidden-id">{{props.item.studentID}}</td>
										<td class="text-left text-xs-left">{{props.item.firstName}}</td>
										<td class="text-left text-xs-left">{{props.item.lastName}}</td>
										<td class="text-xs-left">{{ props.item.rol_usm }}</td>
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
  const Students = {
		name: 'Students',
		components:{
			FormCreate,
			Actions
		},
        metaInfo: {
			title: 'Estudiantes',
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
					dialog: false,
					headers: [
					{
						text: 'Id',
						value: 'id',
						class: 'hidden-id',
						sortable: true
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
					{ 	text: 'Rol USM',
						align: 'left',
						sortable: true,
						value: 'rol_usm'
					}],
					loading: false,
					students: [],
					currentStudent: {},
					message: null,
					rows: window.rows,
					page: 1,
					per_page: 10,
					selected_rows: []
                };
        },
        created: function () {
        },
        mounted() {
                api.getAllStudents()  
									.then(response => {  
									this.$log.debug("Data loaded: ", response.data); 
									this.students = response.data;
									this.$log.debug("students : ", this.students); 
									
									})  
									.catch(error => {  
									this.$log.debug(error)  
									this.error = "Failed to load students"  
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

				export  default Students;
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