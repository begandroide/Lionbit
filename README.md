# Lionbit ISW

## Como correr el proyecto

En la carpeta server:
```bash
mvn spring-boot:run
```
En la carpeta client:
```
npm install
npm run serve
```

## Servicio para aplicación SGA

### Entidades
1. Asignatura
2. Profesor
3. Malla
4. Semestre
5. Notas
6. Asistencia
7. Sylabus

Ahora, listamos los microservicios y sus funciones específicas dentro de la aplicación:

#### User-Service
Encargado de definir usuarios, como profesor, jefe de carrera, secretaria, base de datos, mas que nada es la creación de usuarios con los roles específicos.
- Registrar usuario con Rol, nombre de usuario y contraseña.
- Editar, eliminar usuario.
- Listar usuarios con roles.

#### Assignature-Service
Encargado de asignaturas tanto históricas como las en curso. 
- Registrar asignatura
- Lista de alumnos en una asignatura
- Definir profesor para asignatura
#### Sylabus-Service
Encargado de administrar la planificación de actividades y evaluaciones en la asignatura.
- Registrar tareas a una asignatura.
- Registrar controles a una asignatura.
- Registrar certamen a una asignatura.
- Registrar actividad a una asignatura. 
- Todo con fecha por hacer.
#### Malla-Service
Encargado de administrar la versión de malla vigente, de aquí salen las asignaturas vigentes de la carrera.
- Registrar versión de malla en la carrera.
- Registrar jefe de carrera a malla.
#### Notas-Asistencia-Service
Encargado de registrar notas de hitos programados en el sylabus y asistencia del alumno a una asignatura
- Registrar nota/evaluación dentro del sylabus de una asignatura.
- Registrar asistencia de alumno en una asignatura.
#### Semestre-Service
Encargado de llevar el semestre vigente en la carrera.
- Registrar semestre vigente.
#### Notification-Service
Encargado de disparar notificaciones sobre actividades por hacer del profesor en su asignatura.
- Mostrar notificación de actividad por hacer.
#### Analytics-Reportery-Service
Encargado de mostrar análisis de datos y reportería de los mismos.

### Comunicación entre servicios
1. Para registrar asignatura debemos ir hacía Semestre-Service y obtener el vigente, luego ir al Malla-Service para obtener la asignatura a registrar en el semestre vigente.
2. Para registrar el sylabus de una asignatura, debemos tener la asignatura vigente, para ello consultamos a Assiganture-Service, quizás tambien haga falta preguntarle a Semestre-Service por el semestre vigente.
3. Para registrar un jefe de carrera a la malla, consultar a User-Service por jefes de carrera.
4. Para registrar asistencia y notas de un alumno en una asignatura, debemos obtener los hitos de Sylabus-Service y obtener el estudiante de Assignature-Service.
