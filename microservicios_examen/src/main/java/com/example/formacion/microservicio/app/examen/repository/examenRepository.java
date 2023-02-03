package com.example.formacion.microservicio.app.examen.repository;

import java.util.List;



import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.formacion.microservicio.examenes.models.Examen;

//import com.example.formacion.microservicio.app.examen.models.Examen;

@Repository
public interface examenRepository extends PagingAndSortingRepository<Examen, Long> {
	@Query("select e from Examen e where e.nombre like %?1%")
	public List<Examen> findByNombre(String nombre);
}
