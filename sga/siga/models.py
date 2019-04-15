from django.db import models

# Create your models here.

class Asignatura(models.Model):
 nombre_asignatura = models.CharField(max_length=100)
 sigla_asignatura = models.CharField(max_length=10)
 cantidad_estudiantes_maximo = models.IntegerField()

 def __str__(self):
  return self.nombre_asignatura

class Estudiante(models.Model):
 nombre = models.CharField(max_length=200)
 semestre_cursa = models.DateTimeField(null=True,blank=True)
 
 def __str__(self):
  return self.nombre

class Profesor(models.Model):
 nombre = models.CharField(max_length=200)
 
 def __str__(self):
        return self.nombre

class AsignaturasProfesor(models.Model):
 asignatura = models.ForeignKey("Asignatura", on_delete=models.CASCADE)
 profesor = models.ForeignKey("Profesor", on_delete=models.CASCADE)
 
 def __str__(self):
  return "{} - {}".format(
            self.asignatura,
            self.profesor
        )
 
class AsignaturasAlumno(models.Model):
 asignatura = models.ForeignKey("AsignaturasProfesor", on_delete=models.CASCADE)
 alumno = models.ForeignKey("Estudiante", on_delete=models.CASCADE)
 

