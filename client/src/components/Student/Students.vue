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
									<Form :students="students" />
								</div >
							</div>
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
								<template v-slot:items="props" >
									<td>{{props.item.id}}</td>
									<td>{{props.item.name}}</td>
									<td class="text-xs-left">{{ props.item.rol_usm }}</td>
									<td class="justify-center layout px-0">
										<v-icon
											small
											class="mr-2"
											@click="editItem(props.item)"
										>
											edit
										</v-icon>
										<v-icon
											small
											@click="deleteStudent(props.item.id)"
										>
											delete
										</v-icon>
										</td>
								</template>
								<template v-slot:no-results>
									<v-alert :value="true" color="error" icon="warning">
									Your search for "{{ search }}" found no results.
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
import Form from "./Form";
// app Vue instance
  const Students = {
		name: 'Students',
		components:{
			Form
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
					search: '',
					dialog: false,
					headers: [
					{
						text: 'Id',
						value: 'id',
						sortable: true
					},
					{
						text: 'Nombre del estudiante',
						align: 'left',
						sortable: true,
						value: 'name'
					},
					{ 	text: 'Rol USM',
						align: 'left',
						sortable: true,
						value: 'rol_usm'
					},
					{
						text: 'Acciones',
						value: 'acciones'
					}],
					loading: false,
					students: [],
					currentStudent: {},
					message: null,
                };
        },
        created: function () {
        },
        mounted() {
                api.getAllStudents()  
                        .then(response => {  
                        this.$log.debug("Data loaded: ", response.data.content); 
                        this.students = response.data.content;
                        this.$log.debug("students : ", this.students); 
                        
                        })  
                        .catch(error => {  
                        this.$log.debug(error)  
                        this.error = "Failed to load students"  
                        })  
                        .finally(() => this.loading = false)  
                // this.newStudent = {
                //         nombre: null,
                // };
        },
        methods: {
			deleteStudent(id) {
				this.loading = true;
				api.removeForId(id)
					.then((response) => {
						this.$log.debug(response);
						let index = this.students.findIndex(x => x.id == id);
						this.$delete(this.students,index);
						})
					.catch((err) => {
						this.$log.debug(err); 
						this.loading = false;
					})
					.finally(() => this.loading = false) 
			},
		},
  }

        export  default Students
</script>

<style>
  [v-cloak] { display: none; }
</style>