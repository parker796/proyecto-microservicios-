package com.example.formacion.microservicio.app.cursos.controllers;

import controllers.CommontControllerGeneric;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.formacion.microservicio.app.cursos.entity.Curso;
import com.example.formacion.microservicio.app.cursos.services.CourseService;
import com.example.formacion.microservicio.common.students.models.entity.Student;

import java.util.List;
import java.util.Optional;

@RestController
public class CourseController extends CommontControllerGeneric<Curso, CourseService>{
 
	public CourseController(CourseService sServiceGeneric) {
		super(sServiceGeneric);
		// TODO Auto-generated constructor stub
	}
	
	//se maneja como un putmapping porque editamos el curso con la lista de la relacion en vacion puede o no tener alumnos tenemos opcionalidad
	@PutMapping("/{id}/asignar-alumnos") //id del curso y asignacion del alumno
	public ResponseEntity<?> asignarAlumnos(@PathVariable Long id, @RequestBody List<Student> alumnos){
		Optional<Curso> o = this.sServiceGeneric.findById(id);
		System.out.println("el id del curso es: " + id + " el nombre del curso es: " + o.get().getName() +
				" al igual del id encontrado debe ser igual al primero " + o.get().getId() );
		if(o.isEmpty()) { //hay como un error con o.isPresent mejor como isEmpty()
			return ResponseEntity.notFound().build(); //404
		}
		Curso courseDb = o.get();
		alumnos.forEach(a -> {
			courseDb.addStudent(a); //por cada curso encontrado agregamos un alumno a la lista donde esta la relacion para agregar
		});
		return ResponseEntity.status(HttpStatus.CREATED).body(this.sServiceGeneric.save(courseDb));
	}
	//eliminamos un alumno relacionado al curso
	@PutMapping("/{id}/eliminar-alumnos")
	public ResponseEntity<?> eliminarAlumno(@PathVariable Long id, @RequestBody Student alumnos){
		Optional<Curso> o = this.sServiceGeneric.findById(id);
		if(o.isEmpty()) {
			return ResponseEntity.notFound().build(); //404
		}
		Curso courseDb = o.get();
		courseDb.removeStudent(alumnos); //eliminamos alumno uno por uno mas personalizado
		
		return ResponseEntity.status(HttpStatus.CREATED).body(this.sServiceGeneric.save(courseDb));
	}
	
	@GetMapping("/alumno/{id}")
	public ResponseEntity<?> filtrarIdAlumnoPorCurso(@PathVariable Long id){
		Curso curso = sServiceGeneric.findCursoByAlumnoId(id);
		return ResponseEntity.ok(curso);
	}
	
	


}
