package com.example.formacion.microservicio.app.examen.repository;

import org.springframework.data.repository.CrudRepository;


import com.example.formacion.microservicio.examenes.models.Asignatura;
//aqui no aplica la paginacion porque no tiene su controlador generico como tal
public interface AsignaturaRepository extends CrudRepository<Asignatura, Long> {
		
}
