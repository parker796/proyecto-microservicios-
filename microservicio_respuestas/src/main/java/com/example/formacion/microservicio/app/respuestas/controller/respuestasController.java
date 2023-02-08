package com.example.formacion.microservicio.app.respuestas.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.formacion.microservicio.app.respuestas.models.Respuesta;
import com.example.formacion.microservicio.app.respuestas.services.respuestaService;

@RestController
public class respuestasController {
	
	private respuestaService respServ;
	@Autowired
	public respuestasController(respuestaService respServ) {
		this.respServ = respServ;
	}
	
	@PostMapping
	public ResponseEntity<?> crear(@RequestBody Iterable<Respuesta> respuestas) {
		return ResponseEntity.status(HttpStatus.CREATED).body(respServ.saveAll(respuestas));
	}
	
	
	@GetMapping("/alumno/{alumnoId}/examen/{examenId}")
	ResponseEntity<?> filtrarRespuestasPorAlumnoPorExamen(@PathVariable Long alumnoId, @PathVariable Long examenId){
		Iterable<Respuesta> respuestaDb = respServ.findRespuestaByAlumnoByExamen(alumnoId, examenId);
		return ResponseEntity.ok(respuestaDb);
	}
	
	@GetMapping("/examenes-respondidos/alumno/{alumnoId}")
	ResponseEntity<?> filtrarExamenesByIDSRespuestaByAlumno(@PathVariable Long alumnoId){
		Iterable<Long> respuestaDb = respServ.findExamenesIdsConRespuestasByAlumno(alumnoId);
		return ResponseEntity.ok(respuestaDb);
	}
	
	

}
