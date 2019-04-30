<template>
        <div>
                <div >
                        <h1>Lista de Profesores
                                <b-button variant="success" v-b-modal.modal-prevent-closing>Añadir profesor</b-button>
                        </h1>
                        <section class="todoapp">
                                <table class="table">
                                        <thead>
                                                <tr>
                                                        <th scope="col">Id</th>
                                                        <th scope="col">Nombre</th>
                                                        <th scope="col">Rut</th>
                                                </tr>
                                        </thead>
                                        <tbody>
                                                <tr v-for="(profesor) in teachers" :key="profesor.id">
                                                        <td> {{profesor.id}} </td>
                                                        <td> {{profesor.name}} </td>
                                                        <td> {{profesor.rut}} </td>
                                                        <!-- <td>
                                                                <button class="btn btn-info"
                                                                        v-on:click="getProfesor(profesor.id)">Edit</button>
                                                                <button class="btn btn-danger"
                                                                        v-on:click="deleteProfesor(profesor.id)">Delete</button> -->
                                                        <!-- </td> -->
                                                </tr>
                                        </tbody>
                                </table>
                        </section>
                </div>
                <div class="loading" v-if="loading===true">Loading&#8230;</div>

<!-- Modal añadir profesor -->
                <b-modal
                        id="modal-prevent-closing"
                        ref="modal"
                        title="Submit Your Name">
                        <form ref="form" @submit.prevent="handleSubmit">
                                <b-form-group
                                        :state="newTeacher.name"
                                        label="Name"
                                        label-for="name-input"
                                        invalid-feedback="Name is required">
                                        <b-form-input
                                        id="name-input"
                                        v-model="newTeacher.name"
                                        :state="newTeacher.name"
                                        required>
                                        </b-form-input>
                                </b-form-group>
                                <b-form-group
                                        :state="newTeacher.rut"
                                        label="Rut"
                                        label-for="rut-input"
                                        invalid-feedback="Rut is required">
                                        <b-form-input
                                                id="rut-input"
                                                v-model="newTeacher.rut"
                                                :state="newTeacher.rut"
                                                required
                                        ></b-form-input>
                                        <b-button type="submit" variant="primary">Submit</b-button>
                                        <b-button type="reset" variant="danger">Reset</b-button>
                                </b-form-group>
                        </form>
                </b-modal>
        </div>
</template>

<script>
import api from '../Api';

// app Vue instance
  const Teachers = {
    name: 'Teachers',
    props: {
      activeUser: Object
    },
        data: function(){
                return{
                        teachers: [],
                        delimiters: ['${ ', ' }'],
                        loading: false,
                        message: null,
                        newTeacher: {
                                name: null,
                                rut: null
                        },
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
                        this.error = "Failed to load todos"  
                        })  
                        .finally(() => this.loading = false)  
        },
        methods: {
                 handleSubmit() {
                         /// postear un profesor
                        // Exit when the form isn't valid
                        // if (!this.checkFormValidity()) {
                        // return
                        this.$log.debug("New item created:", this.newTeacher) 
                        api.createNewTeacher(this.newTeacher, false).then( (response) => {  
                        this.$log.debug("New item created:", response);  
                                this.teachers.push({  
                                id: response.data.id,  
                                        name: this.newTeacher.name,  
                                        rut:   this.newTeacher.rut 
                                })  
                        }).catch((error) => {  
                        this.$log.debug(error);  
                                this.error = "Failed to add todo"  
                                });  
                        // Hide the modal manually
                        this.$nextTick(() => {
                        this.$refs.modal.hide()
                        })
                },
        },
  }

        export  default Teachers
</script>

<style>
  [v-cloak] { display: none; }
</style>