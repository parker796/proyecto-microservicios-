package com.example.formacion.microservicio.app.cursos.repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.formacion.microservicio.app.cursos.entity.Curso;
@Repository
public interface CourseRepository extends CrudRepository<Curso,Long>{
	@Query("select c from Curso c join fetch c.Students s where s.id=?1")
	public Curso findCursoByAlumnoId(Long id); //el id del alumno relacionado con un id dentro del curso

}
