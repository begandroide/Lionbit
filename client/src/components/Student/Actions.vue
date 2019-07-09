<template>
    <div class="card-body">
        <div class="row">
            <div class="col-lg-8 col-sm-12">
                <div align="left">
                    <div class="btn-group">
                        <v-btn color="info" round :disabled="(selected == null)">
                            <i class="fa fa-eye"></i> Detalles
                        </v-btn>
                        <FormEdit :newStudent="selected" />
                        <FormDelete :objeto="selected" />
                    </div>
                </div>
            </div>
            <div class="col-lg-4 col-sm-12">
                <div class="text-right">
                    <p class="text-muted" v-if="selected != null">{{selected.name}}</p>
                </div>
            </div>
        </div>
    </div>
</template>

<style>
</style>

<script>
import FormEdit from './FormEdit';
import FormDelete from './FormDelete';
  const Actions = {
    name: 'Actions',
    components:{
        FormEdit,
        FormDelete
    },
	props:{
        selected: Object,
	},
    data: function() {
        return {
        };
    },
    methods:{
        myEventHandler(){
            this.$log.debug(this.selected);
        }
    },
  }
  export default Actions
</script>
