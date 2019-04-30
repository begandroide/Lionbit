<template>
<div>
        <div >
                        <h1>Lista de Estudiante
                                <button class="btn btn-success" data-toggle="modal"
                                        data-target="#">Añadir Estudiante</button>
                        </h1>
                        <section class="todoapp">
                        <table class="table">
                                <thead>
                                        <tr>
                                                <th scope="col">Id</th>
                                                <th scope="col">Nombre</th>
                                                <th scope="col">Rol</th>
                                        </tr>
                                </thead>
                                <tbody>
                                        <tr v-for="(profesor) in students" :key="profesor.id">
                                                <td> {{profesor.id}} </td>
                                                <td> {{profesor.name}} </td>
                                                <td> {{profesor.rol_usm}} </td>
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

 
</div>
</template>

<script>

import api from '../Api';

// app Vue instance
  const Students = {
    name: 'Students',
    props: {
      activeUser: Object
    },
        data: function(){
                return{
                        students: [],
                        delimiters: ['${ ', ' }'],
                        loading: false,
                        message: null,
                        newStudent: {
                                nombre: null,
                        },
                };
        },
        created: function () {
                // this.getArticles();
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
                        this.error = "Failed to load todos"  
                        })  
                        .finally(() => this.loading = false)  
                // this.newStudent = {
                //         nombre: null,
                // };
        },
        methods: {
                // getArticles() {
                //         this.loading = true;
                //         this.$http.get('localhost:8088/students/')
                //                 .then((response) => {
                //                         this.students = response.data;
                //                         this.loading = false;
                //                 })
                //                 .catch(() => {
                //                         this.loading = false;
                //                 })
                // },
                // addProfesor() {
                //         this.loading = true;
                //         this.$http.post('localhost:8088/students/', this.newStudent)
                //                 .then(() => {
                //                         this.loading = false;
                //                         this.getArticles();
                //                 })
                //                 .catch(() => {
                //                         this.loading = false;
                //                 })
                // },
                // deleteProfesor(id) {
                //         this.loading = true;
                //         this.$http.delete(`localhost:8088/students/${id}/` )
                //             .then( () => {
                //                 this.loading = false;
                //                 this.getArticles();
                //             })
                //             .catch(() => {
                //               this.loading = false;
                //             })
                //        }
        },
  }

        export  default Students
</script>

<style>
  [v-cloak] { display: none; }
</style>