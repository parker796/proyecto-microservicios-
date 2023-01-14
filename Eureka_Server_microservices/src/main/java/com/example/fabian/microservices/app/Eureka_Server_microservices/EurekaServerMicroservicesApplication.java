package com.example.fabian.microservices.app.Eureka_Server_microservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer //habilitamos eureka server
@SpringBootApplication
public class EurekaServerMicroservicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaServerMicroservicesApplication.class, args);
	}

}
