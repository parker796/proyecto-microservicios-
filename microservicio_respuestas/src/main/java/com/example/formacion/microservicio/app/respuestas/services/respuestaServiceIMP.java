package com.example.formacion.microservicio.app.respuestas.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.formacion.microservicio.app.respuestas.models.Respuesta;
import com.example.formacion.microservicio.app.respuestas.repository.respuestaRepository;
@Service
public class respuestaServiceIMP implements respuestaService{

	
	@Autowired
	private respuestaRepository repository; //intectamos la respuesta repository en el service implements
	
	@Override
	@Transactional
	public Iterable<Respuesta> saveAll(Iterable<Respuesta> respuestas) {
		
		return repository.saveAll(respuestas);
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Respuesta> findRespuestaByAlumnoByExamen(Long alumnoId, Long examenID) {
		
		return repository.findRespuestaByAlumnoByExamen(alumnoId, examenID);
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Long> findExamenesIdsConRespuestasByAlumno(Long alumnoId) {
		
		return repository.findExamenesIdsConRespuestasByAlumno(alumnoId);
	}
	

}
