<template>
    <div class="card-body">
        <div class="row">
            <div class="col-lg-10 col-sm-12">
                <div align="left">
                    <div class="btn-group" id="botonesConfiguracionListado">
                            <button class="btn btn-secondary disabled" onclick="genericAction(this)" name="mostrar" url="">
                                <i class="fa fa-eye"></i> Detalles
                            </button>
                            <button class="btn btn-primary disabled" onclick="genericAction(this)" name="editar" url="">
                                <i class="fa fa-edit"></i> Editar
                            </button>
                            <button class="btn btn-danger disabled" onclick="genericAction(this)" name="eliminar" url="">
                                <i class="fa fa-trash"></i> Eliminar
                            </button>
                    </div>
                </div>
            </div>
            <div class="col-lg-2 col-sm-12">
                <div class="text-right">
                    <p class="text-muted" name="selected-entity">{{info}}</p>
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
		info: "",
	},
    data: () => ({
    })
  }
  export default Actions
</script>
