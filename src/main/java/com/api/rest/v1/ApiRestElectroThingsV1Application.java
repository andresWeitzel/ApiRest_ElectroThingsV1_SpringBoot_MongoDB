package com.api.rest.v1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;

@SpringBootApplication
@EnableWebMvc
public class ApiRestElectroThingsV1Application {

	public static void main(String[] args) {
		SpringApplication.run(ApiRestElectroThingsV1Application.class, args);
	}

}
