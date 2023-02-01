package com.example.formacion.microservicio.app.cursos.services;

import com.example.formacion.microservicio.app.cursos.entity.Curso;
import services.CommonServiceGeneric;
public interface CourseService extends CommonServiceGeneric<Curso>{
	public Curso findCursoByAlumnoId(Long id); 
}
