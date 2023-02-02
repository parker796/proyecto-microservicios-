package com.example.formacion.microservicio.app.examen;

import org.springframework.boot.SpringApplication;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
//import com.example.formacion.microservicio.examenes.models.Examen;
@SpringBootApplication
@EnableEurekaClient
@EntityScan("com.example.formacion.microservicio.examenes.models") //aqui generaba un error al  crear el bean examen
//entonces se manejo esta ruta para quitarlo
public class MicroserviciosExamenApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviciosExamenApplication.class, args);
	}

}
