from django.contrib import admin

# Register your models here.

from .models import Asignatura, Profesor,Estudiante, AsignaturasAlumno, AsignaturasProfesor

admin.site.register(Asignatura)
admin.site.register(Profesor)
admin.site.register(AsignaturasAlumno)
admin.site.register(AsignaturasProfesor)
admin.site.register(Estudiante)