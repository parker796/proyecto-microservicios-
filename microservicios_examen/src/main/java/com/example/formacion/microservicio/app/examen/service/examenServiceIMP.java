package com.example.formacion.microservicio.app.examen.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.formacion.microservicio.app.examen.repository.AsignaturaRepository;
//import com.example.formacion.microservicio.app.examen.models.Examen;
import com.example.formacion.microservicio.app.examen.repository.examenRepository;
import com.example.formacion.microservicio.examenes.models.Asignatura;
import com.example.formacion.microservicio.examenes.models.Examen;
import services.CommonServiceGenericImp;

@Service
public class examenServiceIMP extends CommonServiceGenericImp<Examen, examenRepository>implements examenService{
	
	@Autowired //igual lo pudimos incluir en el constructor inyeccion por constructor buenas practicas
	private AsignaturaRepository asignaturaRepository;
	
	public examenServiceIMP(examenRepository repository) {
		super(repository);
		// TODO Auto-generated constructor stub
	}
	

	@Override
	@Transactional(readOnly = true)
	public List<Examen> findByNombre(String nombre) {
		return repository.findByNombre(nombre);
	}

	@Override
	public Iterable<Asignatura> findAllAsignaturas() {
		// TODO Auto-generated method stub
		return  asignaturaRepository.findAll();
	}



	

}
