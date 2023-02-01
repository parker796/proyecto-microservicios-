package com.example.formacion.microservicio.app.examen.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.formacion.microservicio.app.examen.models.Examen;

@Repository
public interface examenRepository extends CrudRepository<Examen, Long> {

}
