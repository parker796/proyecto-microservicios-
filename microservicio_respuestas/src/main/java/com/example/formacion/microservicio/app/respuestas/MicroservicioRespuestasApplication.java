package com.example.formacion.microservicio.app.respuestas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
@EntityScan({"com.example.formacion.microservicio.app.respuestas.models",
			"com.example.formacion.microservicio.common.students.models.entity",
			"com.example.formacion.microservicio.examenes.models"})
public class MicroservicioRespuestasApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicioRespuestasApplication.class, args);
	}

}
