package com.example.formacion.microservicio.app.examen.service;

import java.util.List;

import com.example.formacion.microservicio.examenes.models.Asignatura;
//import com.example.formacion.microservicio.app.examen.models.Examen;
import com.example.formacion.microservicio.examenes.models.Examen;
import services.CommonServiceGeneric;

//Dao Management podria estar con varios repositorios es como fachada puede ir dentro del mismo service
public interface examenService extends CommonServiceGeneric<Examen> {
	public List<Examen> findByNombre(String nombre);
	public Iterable<Asignatura> findAllAsignaturas();
	
}
