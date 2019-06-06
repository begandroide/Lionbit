
<template>
    <v-dialog
      v-model="dialog"
      width="500"
    >
    <template v-slot:activator="{ on }">
      <v-tooltip bottom>
        <template v-slot:activator="{ on }">
          <v-icon
            v-on="on"
            small
            color="red lighten-2"
            dark
            class="mr-2"
            @click="dialog = true"
          >
            delete
          </v-icon>
        </template>
        <span>Eliminar este profesor</span>
      </v-tooltip>
    </template>

      <v-card>
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
            color="warning"
            flat
            @click="deleteTeacher(objeto.id)"
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
</template>

<script>
import api from '../../Api';

    const ActionDialog = {
        name: 'ActionDialog',
        props:{
            objeto: Object,
            teachers: []
        },
        data () {
          return {
            dialog: false
          }
        },
        methods:{
          deleteTeacher(id) {
				this.loading = true;
				api.removeForIdTeacher(id)
					.then((response) => {
						this.$log.debug(response);  
						let index = this.teachers.findIndex(x => x.id == id);
						this.$delete(this.teachers,index);
					})
					.catch((err) => {
						this.$log.debug(err); 
						this.loading = false;
					})
					.finally(() => {
            this.loading = false;
            this.dialog = false;
            }) 
				}
        }

    };
    export default ActionDialog;
</script>