package com.example.formacion.microservicio.app.examen.repository;

import org.springframework.data.repository.CrudRepository;


import com.example.formacion.microservicio.examenes.models.Asignatura;

public interface AsignaturaRepository extends CrudRepository<Asignatura, Long> {
		
}
