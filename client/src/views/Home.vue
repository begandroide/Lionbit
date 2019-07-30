<template>
<div class="m-5">
  <div class="mt-3" :key="index" v-for="(item,index) in asignaturasSemestre">
    <b-card-group deck>
      <b-card :key="index2" v-for="(item2,index2) in item" bg-variant="primary" text-variant="white" :header="item2" class="text-center">
        <b-card-text>Programación</b-card-text>
      </b-card>
    </b-card-group>
  </div>
  <div class="mt-3">
    <b-card-group deck>
      <b-card bg-variant="info" text-variant="white" header="Info" class="text-center">
        <b-card-text>Programación</b-card-text>
      </b-card>

      <b-card bg-variant="warning" text-variant="white" header="Warning" class="text-center">
        <b-card-text>Programación</b-card-text>
      </b-card>

      <b-card bg-variant="danger" text-variant="white" header="Danger" class="text-center">
        <b-card-text>Programación</b-card-text>
      </b-card>
    </b-card-group>
  </div>
  <div class="mt-3">
    <b-card-group deck>
      <b-card bg-variant="light" header="Light" class="text-center">
        <b-card-text>Programación</b-card-text>
      </b-card>

      <b-card bg-variant="dark" header="Dark" text-variant="white" class="text-center">
        <b-card-text>Programación</b-card-text>
      </b-card>

      <b-card header="Default" class="text-center">
        <b-card-text>Programación</b-card-text>
      </b-card>
    </b-card-group>
  </div>
</div>
</template>


<script>  
export default {
  name: 'Home',
  data: () =>
  {
    return {
      numeroSemestres : 8,
      asignaturasSemestre:
      [
        ["Programación","Matemáticas I","Introducción a la física","Humanístico I","Educación fisica I"],
        ["Programación","Matemáticas I","Introducción a la física","Humanístico I","Educación fisica I"],
        ["Programación","Matemáticas I","Introducción a la física","Humanístico I","Educación fisica I"],
        ["Programación","Matemáticas I","Introducción a la física","Humanístico I","Educación fisica I"],
        ["Programación","Matemáticas I","Introducción a la física","Humanístico I","Educación fisica I"],
        ["Programación","Matemáticas I","Introducción a la física","Humanístico I","Educación fisica I"],
        ["Programación","Matemáticas I","Introducción a la física","Humanístico I","Educación fisica I"],
        ["Programación","Matemáticas I","Introducción a la física","Humanístico I","Educación fisica I"],

      ]
      }
  }
}
  
</script>  
