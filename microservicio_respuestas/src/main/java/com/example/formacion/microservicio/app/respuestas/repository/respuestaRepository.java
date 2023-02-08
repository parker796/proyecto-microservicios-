package com.example.formacion.microservicio.app.respuestas.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.formacion.microservicio.app.respuestas.models.Respuesta;

public interface respuestaRepository extends CrudRepository<Respuesta, Long> {
	/*
	 * @Query("select r from Respuesta r join fetch r.alumno a join fetch r.pregunta p join fetch p.examen e where a.id = ?1 and e.id = ?2")
	public Iterable<Respuesta> findRespuestaByAlumnoByExamen(Long alumnoId, Long examenID);
	//asi como esta si funciona pero por cada examen a su vez imprime id de la respuesta y id alumno imprime 
	//repetidas veces ese examen con tales respuesta del alumno asociado entonces necesitamos agruparlo con
	//el id del examen una sola vez por las preguntas respondidas al id del alumno
	@Query("select e.id from Respuesta r join r.alumno a join r.pregunta p join p.examen e where a.id = ?1 group by e.id")
	public Iterable<Long> findExamenesIdsConRespuestasByAlumno(Long alumnoId);
	*/

	@Query("select r from Respuesta r join fetch r.alumno a join fetch r.pregunta p join fetch p.examen e where a.id = ?1 and e.id = ?2")
	public Iterable<Respuesta> findRespuestaByAlumnoByExamen(Long alumnoId, Long examenID);
	
	@Query("select e.id from Respuesta r join r.alumno a join r.pregunta p join p.examen e where a.id = ?1 group by e.id")
	public Iterable<Long> findExamenesIdsConRespuestasByAlumno(Long alumnoId);

}
