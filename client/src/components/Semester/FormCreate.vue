<template>
  <v-layout row justify-center>
    <v-dialog v-model="dialog" persistent max-width="600px">
      <template v-slot:activator="{ on }">
        <v-btn color="primary" dark v-on="on">Añadir nuevo semestre</v-btn>
      </template>
      <v-card>
        <v-card-title>
          <span class="headline">Nuevo semestre</span>
        </v-card-title>
            <form ref="form" @submit.prevent="handleSubmit2">
        <v-card-text>
          <v-container grid-list-md>
               <v-layout wrap>
                <v-flex xs12 sm6 md6>
                   <v-select
                      :items="years"
                      label="Año"
                      v-model="newSemester.yearSemester"
                      required
                    ></v-select>
                </v-flex>
                <v-flex xs12 sm6 md6>
                   <v-select
                      :items="semester"
                      label="Número de semestre"
                      v-model="newSemester.numberSemester"
                      required
                    ></v-select>
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
import Semester from './Semester'

  const FormCreate =  {
      components:{
        Semester,
      },
      props:{
        semesters: Array
      },
      data: () => ({
        dialog: false,
        newSemester: {
          id: 0,
          numberSemester: null,
          yearSemester: null,
        },
        years:["2018","2019","2020","2021","2022"],
        semester:["1","2","3"]
      }),
    methods:{
        handleSubmit2() {
            api.createNewSemester(this.newSemester, false).then( (response) => {  
                console.log(this.$root);
                this.semesters.push({  
                    id: response.data.id,  
                    numberSemester: this.newSemester.numberSemester,  
                    yearSemester:   this.newSemester.yearSemester,
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
  export default FormCreate;
</script>