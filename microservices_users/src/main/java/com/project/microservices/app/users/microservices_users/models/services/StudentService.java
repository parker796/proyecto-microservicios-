package com.project.microservices.app.users.microservices_users.models.services;

import java.util.List;

import com.example.formacion.microservicio.common.students.models.entity.Student;
//import com.project.microservices.app.users.microservices_users.models.entity.Student;

import services.CommonServiceGeneric;


//import java.util.Optional;

public interface StudentService extends CommonServiceGeneric<Student>{
   
	/*public Iterable<Student> findAll(); //puede ser una lista
    public Optional<Student> findById(Long id);
    public Student save(Student student);
    public void deleteById(Long id);*/
	public List<Student> findByNombreAndApellido(String buscar);
}
