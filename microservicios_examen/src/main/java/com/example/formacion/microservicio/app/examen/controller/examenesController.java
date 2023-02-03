package com.example.formacion.microservicio.app.examen.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.formacion.microservicio.app.examen.service.examenService;
//import com.example.formacion.microservicio.app.examen.models.Examen;
import com.example.formacion.microservicio.examenes.models.Examen;
import controllers.CommontControllerGeneric;

@RestController
public class examenesController extends CommontControllerGeneric<Examen, examenService>{
	public examenesController(examenService sServiceGeneric) {
		super(sServiceGeneric);
		// TODO Auto-generated constructor stub
	}

	//actualizamos las preguntas con sus preguntas
	@PutMapping("/{id}")//siempre tiene que ir binding result despues del entity validar
	public ResponseEntity<?> editar(@Valid @RequestBody Examen examen, BindingResult result, @PathVariable Long id){
		if(result.hasErrors()) {
    		return this.validar(result);
    	}
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
	
	@GetMapping("/filtar/{buscar}")
	public ResponseEntity<?> filtrarIdAlumnoPorCurso(@PathVariable String buscar){
		return ResponseEntity.ok(sServiceGeneric.findByNombre(buscar));
	}
	
	@GetMapping("/asignaturas")
	public ResponseEntity<?> filtrarAsignaturas(){
		return ResponseEntity.ok(sServiceGeneric.findAllAsignaturas());
	}

	  protected ResponseEntity<?> validar(BindingResult result){
	    	Map<String, Object> errores = new HashMap<>();
	    	result.getFieldErrors().forEach(err -> {
	    		errores.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
	    	});
	    	return ResponseEntity.badRequest().body(errores);
	    }
}
