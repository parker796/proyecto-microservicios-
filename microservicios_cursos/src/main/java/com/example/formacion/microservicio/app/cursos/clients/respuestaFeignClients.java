package com.example.formacion.microservicio.app.cursos.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "microservicio_respuestas")
public interface respuestaFeignClients {
	@GetMapping("/examenes-respondidos/alumno/{alumnoId}")
	public Iterable<Long> filtrarExamenesByIDSRespuestaByAlumno(@PathVariable Long alumnoId);
}
