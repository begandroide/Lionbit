<template>
<div class="m-5" style=" height:70%;">
  <div class="mt-2" :key="index" v-for="(item,index) in asignaturasSemestre">
    <b-card-group deck>
      
      <b-card :key="index2" v-for="(item2,index2) in item"
         header-text-variant="light"
         :header-bg-variant="item2.color" :header="item2.nombre" header-class="text-center" text-variant="black">
        <b-card-text>
          <div class="row">
            <div class="col-6">
              Créditos 
              <b-badge variant="dark">{{item2.creditos}}</b-badge>
            </div>
            <div class="col-6">
              Alumnos 
              <b-badge variant="success">{{item2.cantAlumnos}}</b-badge>
            </div>
          </div>
          </b-card-text>
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
        [  {nombre:"Programación",creditos:3, cantAlumnos:60, color:"primary"}
          ,{nombre:"Matemáticas I",creditos:3, cantAlumnos:60, color:"primary"}
          ,{nombre:"Introducción a la física",creditos:3, cantAlumnos:60, color:"primary"}
          ,{nombre:"Humanístico I",creditos:3, cantAlumnos:60, color:"primary"}
          ,{nombre:"Educación fisica I",creditos:3, cantAlumnos:60, color:"primary"}
        ],
        [  {nombre:"Programación",creditos:3, cantAlumnos:60, color:"secondary"}
          ,{nombre:"Matemáticas I",creditos:3, cantAlumnos:60, color:"secondary"}
          ,{nombre:"Introducción a la física",creditos:3, cantAlumnos:60, color:"secondary"}
          ,{nombre:"Humanístico I",creditos:3, cantAlumnos:60, color:"secondary"}
          ,{nombre:"Educación fisica I",creditos:3, cantAlumnos:60, color:"secondary"}
        ],
        [  {nombre:"Programación",creditos:3, cantAlumnos:60, color:"success"}
          ,{nombre:"Matemáticas I",creditos:3, cantAlumnos:60, color:"success"}
          ,{nombre:"Introducción a la física",creditos:3, cantAlumnos:60, color:"success"}
          ,{nombre:"Humanístico I",creditos:3, cantAlumnos:60, color:"success"}
          ,{nombre:"Educación fisica I",creditos:3, cantAlumnos:60, color:"success"}
        ],
        [  {nombre:"Programación",creditos:3, cantAlumnos:60, color:"warning"}
          ,{nombre:"Matemáticas I",creditos:3, cantAlumnos:60, color:"warning"}
          ,{nombre:"Introducción a la física",creditos:3, cantAlumnos:60, color:"warning"}
          ,{nombre:"Humanístico I",creditos:3, cantAlumnos:60, color:"warning"}
          ,{nombre:"Educación fisica I",creditos:3, cantAlumnos:60, color:"warning"}
        ],
        [  {nombre:"Programación",creditos:3, cantAlumnos:60, color:"danger"}
          ,{nombre:"Matemáticas I",creditos:3, cantAlumnos:60, color:"danger"}
          ,{nombre:"Introducción a la física",creditos:3, cantAlumnos:60, color:"danger"}
          ,{nombre:"Humanístico I",creditos:3, cantAlumnos:60, color:"danger"}
          ,{nombre:"Educación fisica I",creditos:3, cantAlumnos:60, color:"danger"}
        ],
        [  {nombre:"Programación",creditos:3, cantAlumnos:60, color:"info"}
          ,{nombre:"Matemáticas I",creditos:3, cantAlumnos:60, color:"info"}
          ,{nombre:"Introducción a la física",creditos:3, cantAlumnos:60, color:"info"}
          ,{nombre:"Humanístico I",creditos:3, cantAlumnos:60, color:"info"}
          ,{nombre:"Educación fisica I",creditos:3, cantAlumnos:60, color:"info"}
        ],
      ]
      }
  }
}
  
</script>  
