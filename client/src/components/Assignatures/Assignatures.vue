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
									Asignaturas
								</h3>
								<p class="text-muted card-subtitle"> Listado de Asignaturas registradas en el sistema. </p>
							</div>

							<div class="col-md-4 col-sm-4">
								<div align="right">
									<Form :assignatures="assignatures" />
								</div >
							</div>

							<Actions />
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
								label="Search"
								single-line
								hide-details
							></v-text-field>
							<v-data-table
								:headers="headers"
								:items="assignatures"
								class="elevation-1"
								:search="search"
								:aria-sort="true"
							>
								<template v-slot:items="props" >
									<td class="hidden-id">{{props.item.id}}</td>
									<td class="text-left text-xs-left">{{props.item.name}}</td>
									<td class="text-xs-left">{{ props.item.sigla }}</td>
									<td class="text-left ">
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
import Form from "./Form";
import Actions from "../Common/Actions";
// app Vue instance
  const Assignatures = {
		name: 'Assignatures',
		components:{
			Form,
			Actions
		},
        metaInfo: {
			title: 'Asignaturas',
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
						class: 'hidden-id',
						sortable: true
					},
					{
						text: 'Nombre de asignatura',
						align: 'left',
						sortable: true,
						value: 'name'
					},
					{ 	text: 'Sigla USM',
						align: 'left',
						sortable: true,
						value: 'sigla_usm'
					},
					{ 	text: 'Cantidad alumnos',
						align: 'left',
						sortable: true,
						value: 'cant_alumnos'
					},
					{
						text: 'Acciones',
						value: 'acciones'
					}],
					loading: false,
					assignatures: [],
					message: null,
                };
        },
        created: function () {
        },
        mounted() {
                api.getAllAssignatures()  
                        .then(response => {  
                        this.$log.debug("Data loaded: ", response.data.content); 
                        this.assignatures = response.data.content;
                        this.$log.debug("assignatures : ", this.assignatures); 
                        
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
						let index = this.assignatures.findIndex(x => x.id == id);
						this.$delete(this.assignatures,index);
						})
					.catch((err) => {
						this.$log.debug(err); 
						this.loading = false;
					})
					.finally(() => this.loading = false) 
			},
		},
  }

        export  default Assignatures
</script>

<style>
  [v-cloak] { display: none; }
	.hidden-id{
		display: none;
	}
</style>