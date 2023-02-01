package com.example.formacion.microservicio.app.examen.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.formacion.microservicio.app.examen.service.examenService;
import com.example.formacion.microservicio.app.examen.models.Examen;

import controllers.CommontControllerGeneric;

@RestController
public class examenesController extends CommontControllerGeneric<Examen, examenService>{
	public examenesController(examenService sServiceGeneric) {
		super(sServiceGeneric);
		// TODO Auto-generated constructor stub
	}

	//actualizamos las preguntas con sus preguntas
	@PutMapping("/{id}")
	public ResponseEntity<?> editar(@RequestBody Examen examen, @PathVariable Long id){
		Optional<Examen> o = sServiceGeneric.findById(id);
		if(o.isEmpty()) {
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
