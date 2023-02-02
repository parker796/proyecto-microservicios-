package com.example.formacion.microservicio.app.cursos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@EntityScan({"com.example.formacion.microservicio.common.students.models.entity",
			"com.example.formacion.microservicio.examenes.models",
			"com.example.formacion.microservicio.app.cursos.entity"})//scaneamos el paquete del modelo comun porque se movio del proyecto de este paquete
public class MicroserviciosCursosApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviciosCursosApplication.class, args);
	}

}
