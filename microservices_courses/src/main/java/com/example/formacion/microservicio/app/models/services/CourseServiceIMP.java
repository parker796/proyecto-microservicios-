package com.example.formacion.microservicio.app.models.services;

import org.springframework.stereotype.Service;


import com.example.formacion.microservicio.app.models.entity.Course;
import com.example.formacion.microservicio.app.models.repository.CourseRepository;

import services.CommonServiceGenericImp;

@Service
public class CourseServiceIMP extends CommonServiceGenericImp<Course, CourseRepository> implements CourseService {

	public CourseServiceIMP(CourseRepository repository) {
		super(repository);
		// TODO Auto-generated constructor stub
	}

	

}
