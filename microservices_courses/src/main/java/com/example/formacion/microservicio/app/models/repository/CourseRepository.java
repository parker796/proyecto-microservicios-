package com.example.formacion.microservicio.app.models.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.formacion.microservicio.app.models.entity.Course;

public interface CourseRepository extends CrudRepository<Course, Long> {

}
