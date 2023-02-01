package com.project.microservices.app.users.microservices_users;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient //habilitamos el cliente de nuestro microservicio para eureka
@SpringBootApplication
@EntityScan({"com.example.formacion.microservicio.common.students.models.entity"})//scaneamos el paquete del modelo comun porque se movio del proyecto de este paquete
public class MicroservicesUsersApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicesUsersApplication.class, args);
	}

}
