package com.example.formacion.microservicio.app.cursos.controllers;

import controllers.CommontControllerGeneric;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.example.formacion.microservicio.app.cursos.entity.Curso;
import com.example.formacion.microservicio.app.cursos.services.CourseService;
import com.example.formacion.microservicio.common.students.models.entity.Student;
import com.example.formacion.microservicio.examenes.models.Examen;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

@RestController
public class CourseController extends CommontControllerGeneric<Curso, CourseService> {

	public CourseController(CourseService sServiceGeneric) {
		super(sServiceGeneric);
		// TODO Auto-generated constructor stub
	}

	// se maneja como un putmapping porque editamos el curso con la lista de la
	// relacion en vacion puede o no tener alumnos tenemos opcionalidad
	@PutMapping("/{id}/asignar-alumnos") // id del curso y asignacion del alumno
	public ResponseEntity<?> asignarAlumnos(@PathVariable Long id, @RequestBody List<Student> alumnos) {
		Optional<Curso> o = this.sServiceGeneric.findById(id);
		System.out.println("el id del curso es: " + id + " el nombre del curso es: " + o.get().getName()
				+ " al igual del id encontrado debe ser igual al primero " + o.get().getId());
		if (o.isEmpty()) { // hay como un error con o.isPresent mejor como isEmpty()
			return ResponseEntity.notFound().build(); // 404
		}
		Curso courseDb = o.get();
		alumnos.forEach(a -> {
			courseDb.addStudent(a); // por cada curso encontrado agregamos un alumno a la lista donde esta la
									// relacion para agregar
		});
		return ResponseEntity.status(HttpStatus.CREATED).body(this.sServiceGeneric.save(courseDb));
	}

	// eliminamos un alumno relacionado al curso
	@PutMapping("/{id}/eliminar-alumnos")
	public ResponseEntity<?> eliminarAlumno(@PathVariable Long id, @RequestBody Student alumnos) {
		Optional<Curso> o = this.sServiceGeneric.findById(id);
		if (o.isEmpty()) {
			return ResponseEntity.notFound().build(); // 404
		}
		Curso courseDb = o.get();
		courseDb.removeStudent(alumnos); // eliminamos alumno uno por uno mas personalizado

		return ResponseEntity.status(HttpStatus.CREATED).body(this.sServiceGeneric.save(courseDb));
	}

	@GetMapping("/alumno/{id}")
	public ResponseEntity<?> filtrarIdAlumnoPorCurso(@PathVariable Long id) {
		Curso curso = sServiceGeneric.findCursoByAlumnoId(id);
		return ResponseEntity.ok(curso);
	}

	@PutMapping("/{id}/asignar-examenes") // id del curso y asignacion a los examenes muchos a muchos
	public ResponseEntity<?> asignarExamenes(@PathVariable Long id, @RequestBody List<Examen> examenes) {
		Optional<Curso> o = this.sServiceGeneric.findById(id);
		if (o.isEmpty()) { // hay como un error con o.isPresent mejor como isEmpty()
			return ResponseEntity.notFound().build(); // 404
		}
		Curso courseDb = o.get();
		examenes.forEach(a -> {
			courseDb.addExamenes(a); // por cada curso encontrado agregamos un alumno a la lista donde esta la
										// relacion para agregar
		});

		return ResponseEntity.status(HttpStatus.CREATED).body(this.sServiceGeneric.save(courseDb));
	}

	// eliminamos un alumno relacionado al curso
	@PutMapping("/{id}/eliminar-examen")
	public ResponseEntity<?> eliminarExamen(@PathVariable Long id, @RequestBody Examen examen) {
		Optional<Curso> o = this.sServiceGeneric.findById(id);
		if (o.isEmpty()) {
			return ResponseEntity.notFound().build(); // 404
		}
		Curso courseDb = o.get();
		courseDb.removeExamen(examen); // eliminamos alumno uno por uno mas personalizado

		return ResponseEntity.status(HttpStatus.CREATED).body(this.sServiceGeneric.save(courseDb));
	}

	@PutMapping("/{id}") // siempre tiene que ir bindingresult despues de entity a validar
	public ResponseEntity<?> edit(@Valid @RequestBody Curso curso, BindingResult result, @PathVariable Long id) {
		if (result.hasErrors()) {
			return this.validar(result);
		}
		// Optional<Student> o = studentservice.findById(id);
		Optional<Curso> o = sServiceGeneric.findById(id);
		if (o.isEmpty()) {
			return ResponseEntity.notFound().build(); // 404
		}
		Curso cursoDb = o.get();
		cursoDb.setName(curso.getName()); // este es el parametro que viene en la peticion put
		return ResponseEntity.status(HttpStatus.CREATED).body(sServiceGeneric.save(cursoDb)); // tenemos que persistir
																								// los datos en BD
	}

	protected ResponseEntity<?> validar(BindingResult result) {
		Map<String, Object> errores = new HashMap<>();
		result.getFieldErrors().forEach(err -> {
			errores.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
		});
		return ResponseEntity.badRequest().body(errores);
	}

}
