package com.example.formacion.microservicio.app.examenes;

import org.springframework.boot.SpringApplication;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;


@SpringBootApplication
// bvmbnbv @EnableEurekaClient
@EntityScan("com.example.formacion.microservicio.app.models") //aqui generaba un error al  crear el bean examen
//entonces se manejo esta ruta para quitarlo
public class MicroserviciosExamenesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviciosExamenesApplication.class, args);
	}

}
