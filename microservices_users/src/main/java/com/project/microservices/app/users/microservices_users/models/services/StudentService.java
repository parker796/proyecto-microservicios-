package com.project.microservices.app.users.microservices_users.models.services;

import com.project.microservices.app.users.microservices_users.models.entity.Student;

import java.util.Optional;

public interface StudentService {
    public Iterable<Student> findAll(); //puede ser una lista
    public Optional<Student> findById(Long id);
    public Student save(Student student);
    public void deleteById(Long id);
}