package com.example.formacion.microservicio.app.respuestas.services;




import com.example.formacion.microservicio.app.respuestas.models.Respuesta;


public interface respuestaService {
    public Iterable<Respuesta> saveAll(Iterable<Respuesta> respuestas);
	
	public Iterable<Respuesta> findRespuestaByAlumnoByExamen(Long alumnoId, Long examenID);
	
	public Iterable<Long> findExamenesIdsConRespuestasByAlumno(Long alumnoId);
}
