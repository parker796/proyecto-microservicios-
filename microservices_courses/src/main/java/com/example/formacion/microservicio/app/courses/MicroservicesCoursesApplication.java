package com.example.formacion.microservicio.app.courses;

import org.springframework.boot.SpringApplication;



import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
// import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EntityScan({"com.example.formacion.microservicio.common.students.models.entity",
			"com.example.formacion.microservicio.app.models.entity"})//scaneamos el paquete del modelo comun porque se movio del proyecto de este paquete
// @EnableEurekaClient habilitamos el cliente de nuestro microservicio para eureka
public class MicroservicesCoursesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicesCoursesApplication.class, args);
	}

}
