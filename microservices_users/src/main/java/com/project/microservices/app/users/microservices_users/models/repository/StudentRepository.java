package com.project.microservices.app.users.microservices_users.models.repository;

import com.example.formacion.microservicio.common.students.models.entity.Student;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


//import com.project.microservices.app.users.microservices_users.models.entity.Student;
//este viene de spring data pero no se agrega la etiqueta
@Repository
public interface StudentRepository extends CrudRepository<Student,Long>{
	//hql o jpaql buscar por nombre u apellido al inicio 
	//las consultas se relacion con el nombre de la clase o entidad no con la bd ya real
	@Query("select a from Student a where a.name like %?1% or a.Lastname like %?1% ")
	public List<Student> findByNombreAndApellido(String buscar);
}
