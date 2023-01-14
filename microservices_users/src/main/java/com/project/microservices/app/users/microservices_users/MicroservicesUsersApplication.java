package com.project.microservices.app.users.microservices_users;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient //habilitamos el cliente de nuestro microservicio para eureka
@SpringBootApplication
public class MicroservicesUsersApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicesUsersApplication.class, args);
	}

}
