package com.example.formacion.microservicio.app.models.controllers;


import java.util.List;


import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.formacion.microservicio.app.models.entity.Course;
import com.example.formacion.microservicio.app.models.services.CourseService;
import com.example.formacion.microservicio.common.students.models.entity.Student;

import controllers.CommontControllerGeneric;

@RestController
public class CourseController extends CommontControllerGeneric<Course, CourseService>{

	public CourseController(CourseService sServiceGeneric) {
		super(sServiceGeneric);
		// TODO Auto-generated constructor stub
	}
	@PutMapping("/{id}")
	public ResponseEntity<?> edit(@RequestBody Course course, @PathVariable Long id){
		Optional<Course> o = this.sServiceGeneric.findById(id);
		if(o.isPresent()) {
			return ResponseEntity.notFound().build(); //404
		}
		Course courseDb = o.get();
		courseDb.setName(course.getName());
		return ResponseEntity.status(HttpStatus.CREATED).body(this.sServiceGeneric.save(courseDb));
	}

	//se maneja como un putmapping porque editamos el curso con la lista de la relacion en vacion puede o no tener alumnos tenemos opcionalidad
	@PutMapping("/{id}/asignar-alumnos") //id del curso y asignacion del alumno
	public ResponseEntity<?> asignarAlumnos(@PathVariable Long id, @RequestBody List<Student> alumnos){
		Optional<Course> o = this.sServiceGeneric.findById(id);
		if(o.isPresent()) {
			return ResponseEntity.notFound().build(); //404
		}
		Course courseDb = o.get();
		alumnos.forEach(a -> {
			courseDb.addStudent(a); //por cada curso encontrado agregamos un alumno a la lista donde esta la relacion para agregar
		});
		return ResponseEntity.status(HttpStatus.CREATED).body(this.sServiceGeneric.save(courseDb));
	}
	//eliminamos un alumno relacionado al curso
	@PutMapping("/id/eliminar-alumnos")
	public ResponseEntity<?> eliminarAlumno(@PathVariable Long id, @RequestBody Student alumnos){
		Optional<Course> o = this.sServiceGeneric.findById(id);
		if(o.isPresent()) {
			return ResponseEntity.notFound().build(); //404
		}
		Course courseDb = o.get();
		courseDb.removeStudent(alumnos); //eliminamos alumno uno por uno mas personalizado
		
		return ResponseEntity.status(HttpStatus.CREATED).body(this.sServiceGeneric.save(courseDb));
	}
	
	
	

	
}
