<template>
<div>
        <div >
                <h1 class="title">SGA</h1>
                        <h1>Lista de Estudiante
                               <b-button variant="success" v-b-modal.modal-prevent-closing2>Añadir Estudiante</b-button>
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
                                        <tr v-for="(student) in students" :key="student.id">
                                                <td> {{student.id}} </td>
                                                <td> {{student.name}} </td>
                                                <td> {{student.rol_usm}} </td>
                                                <td>
                                                        <!--<button class="btn btn-info"
                                                                v-on:click="getProfesor(profesor.id)">Edit</button>-->
                                                        <button class="btn btn-danger"
                                                                v-on:click="deleteStudent(student.id)">Delete</button>
                                                </td> 
                                        </tr>
                                </tbody>
                        </table>
                        </section>
                </div>
        <div class="loading" v-if="loading===true">Loading&#8230;</div>
       
                <b-modal
                        id="modal-prevent-closing2"
                        ref="modal"
                        title="Submit Your Name">
                        <form ref="form" @submit.prevent="handleSubmit2">
                                <b-form-group
                                        :state="newStudent.name"
                                        label="Name"
                                        label-for="name-input"
                                        invalid-feedback="Name is required">
                                        <b-form-input
                                        id="name-input"
                                        v-model="newStudent.name"
                                        :state="newStudent.name"
                                        required>
                                        </b-form-input>
                                </b-form-group>
                                <b-form-group
                                        :state="newStudent.rol_usm"
                                        label="Rol"
                                        label-for="rol-input"
                                        invalid-feedback="Rol is required">
                                        <b-form-input
                                                id="rol-input"
                                                v-model="newStudent.rol_usm"
                                                :state="newStudent.rol_usm"
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
                        currentStudent: {},
                        message: null,
                        newStudent: {
                                id: 0,
                                name: null,
                                rol_usm: null,
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
                        this.error = "Failed to load students"  
                        })  
                        .finally(() => this.loading = false)  
                // this.newStudent = {
                //         nombre: null,
                // };
        },
        methods: {
                /*addStudent() {
                        this.loading = true;
                        api.createNewStudent(this.newStudent)
                                .then((response) => {
                                        this.$log.debug(response);                           
                                        //$("#addStudentModal").modal('hide');
                                })
                                .catch((err) => {
                                        this.loading = false;
                                        this.$log.debug(err); 
                                })
                                .finally(() => this.loading = false) 
                },*/
                handleSubmit2() {
                        api.createNewStudent(this.newStudent, false).then( (response) => {  
                                this.students.push({  
                                id: response.data.id,  
                                        name: this.newStudent.name,  
                                        rol_usm:   this.newStudent.rol_usm
                                })  
                        }).catch((error) => {  
                        this.$log.debug(error);  
                                this.error = "Failed to add student"  
                                });  
                        // Hide the modal manually
                        this.$nextTick(() => {
                        this.$refs.modal.hide()
                        })
                },
                deleteStudent(id) {
                        this.loading = true;
                        api.removeForId(id)
                            .then((response) => {
                              this.$log.debug(response);  
                            })
                            .catch((err) => {
                              this.$log.debug(err); 
                              this.loading = false;
                            })
                            .finally(() => this.loading = false) 
                       }
        },
  }

        export  default Students
</script>

<style>
  [v-cloak] { display: none; }
</style>