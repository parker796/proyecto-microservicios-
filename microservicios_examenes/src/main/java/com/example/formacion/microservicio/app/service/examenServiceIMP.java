package com.example.formacion.microservicio.app.service;

import org.springframework.stereotype.Service;

import com.example.formacion.microservicio.app.models.Examen;
import com.example.formacion.microservicio.app.repository.examenRepository;

import services.CommonServiceGenericImp;

@Service
public class examenServiceIMP extends CommonServiceGenericImp<Examen, examenRepository>implements examenService{

	public examenServiceIMP(examenRepository repository) {
		super(repository);
		// TODO Auto-generated constructor stub
	}

	

}