<template>
    <div class="card-body">
        <div class="row">
            <div class="col-lg-10 col-sm-12">
                <div align="left">
                    <div class="btn-group">
                            <button class="btn btn-secondary" :disabled="(selected == null)">
                                <i class="fa fa-eye"></i> Detalles
                            </button>
                            <button class="btn btn-primary" :disabled="(selected == null)" v-on:click="myEventHandler">
                                <i class="fa fa-edit"></i> Editar
                            </button>
                            <button class="btn btn-danger" :disabled="(selected == null)">
                                <i class="fa fa-trash"></i> Eliminar
                            </button>
                    </div>
                </div>
            </div>
            <div class="col-lg-2 col-sm-12">
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
  const Actions = {
    name: 'Actions',
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
