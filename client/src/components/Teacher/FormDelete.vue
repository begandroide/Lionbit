<template>
  <v-layout>
    <v-dialog v-model="dialog" persistent max-width="600px">
      <template v-slot:activator="{ on }">
        <v-btn color="error" round :disabled="(objeto == null)"  v-on="on">
            <i class="fa fa-trash"></i> Eliminar
        </v-btn>
      </template>
      <v-card v-if="objeto!=null">
        <v-card-title
          class="headline grey lighten-2"
          primary-title
        >
          Eliminar {{objeto.name}}
        </v-card-title>

        <v-card-text>
            Estas seguro de eliminar {{objeto.name}} 
        </v-card-text>

        <v-divider></v-divider>

        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
            color="error"
            flat
            @click="deleteStudent(objeto.id)"
          >
            Eliminar
          </v-btn>
          <v-btn
            color="primary"
            flat
            @click="dialog = false"
          >
            Cancelar
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-layout>
</template>

<style>
</style>

<script>
import api from '../../Api';

    const FormDelete = {
        name: 'FormDelete',
        props:{
            objeto: Object,
            students: []
        },
        data () {
          return {
            dialog: false
          }
        },
        methods:{
          
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
        
        }

    };
    export default FormDelete;
</script>