package com.project.microservices.app.users.microservices_users.models.repository;

import com.example.formacion.microservicio.common.students.models.entity.Student;
import org.springframework.data.repository.CrudRepository;
//este viene de spring data pero no se agrega la etiqueta
public interface StudentRepository extends CrudRepository<Student,Long>{

}
