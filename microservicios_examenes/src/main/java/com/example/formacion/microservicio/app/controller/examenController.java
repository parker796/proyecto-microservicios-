package com.example.formacion.microservicio.app.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.formacion.microservicio.app.models.Examen;
import com.example.formacion.microservicio.app.service.examenService;

import controllers.CommontControllerGeneric;

@RestController
public class examenController extends CommontControllerGeneric<Examen, examenService>{

	public examenController(examenService sServiceGeneric) {
		super(sServiceGeneric);
		// TODO Auto-generated constructor stub
	}

	//actualizamos las preguntas con sus preguntas
	@PutMapping("/{id}")
	public ResponseEntity<?> editar(@RequestBody Examen examen, @PathVariable Long id){
		Optional<Examen> o = sServiceGeneric.findById(id);
		if(!o.isPresent()) {
			return ResponseEntity.notFound().build(); //404
		}
		Examen examenDb = o.get();
		examenDb.setNombre(examen.getNombre());
		//List<Pregunta> eliminadas = new ArrayList<>();
	
		
		examenDb.getPreguntas()
		.stream()
		.filter(pdb -> !examen.getPreguntas().contains(pdb))
		.forEach(examenDb::removePregunta);
		
		/*
		 *esto se puede optimizar con un stream
		 * examenDb.getPreguntas().forEach(pdb -> {
			if(!examen.getPreguntas().contains(pdb)) {
				eliminadas.add(pdb);
			}
		});
		*/
		
	//	eliminadas.forEach(examenDb::removePregunta);
		examenDb.setPreguntas(examen.getPreguntas());
		return ResponseEntity.status(HttpStatus.CREATED).body(sServiceGeneric.save(examenDb));
		
		
		
	}
}
