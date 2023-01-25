package com.example.formacion.microservicio.app.models.controllers;


import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.formacion.microservicio.app.models.entity.Course;
import com.example.formacion.microservicio.app.models.services.CourseService;

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

	
}
