package com.api.rest.v1.swagger.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


/*
@Configuration
public class SwaggerConfig implements WebMvcConfigurer{

	
	@Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .securityContexts(Arrays.asList(securityContext()))
                .securitySchemes(Arrays.asList(apiKey()))
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    private ApiKey apiKey(){
        return new ApiKey("JWT", "Authorization", "header");
    }

    private SecurityContext securityContext(){
        return SecurityContext.builder().securityReferences(defaultAuth()).build();
    }

    private List<SecurityReference> defaultAuth(){
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Arrays.asList(new SecurityReference("JWT", authorizationScopes));
    }

    //"ApiRest Productos Supermercado v3.0"
    private ApiInfo apiInfo(){
        return new ApiInfo(
        		"ApiRest Productos Supermercado",
        		"* Api Rest para el Microservice App_MicroFrontEnd_Productos implementada con Spring Boot, Spring MVC, Spring Security, JWT , Spring Data JPA, SpringDoc-OpenApi, Swagger UI, Maven, Lombok, Postman, Log4j, Git, DBeaver, pgAdmin y PostgreSQL.\r\n"
        	      		+ "* La Api Rest implementa todas las Operaciones CRUD, tanto para productos como para usuarios. \r\n"
        	      		+ "* Se separa la capa de seguridad para la autenticación , implementando Spring Security y JWT. Además de realizar las operaciones CRUD para usuarios se aplica login y signin para la capa de presentación.\r\n"
        	      		+ "* También se desarrollan los métodos de búsquedas independientes de tipo Like para todos los campos, tanto de usuarios como productos.\r\n"
        	      		+ "* Los objetos de tipo getBy se manipulan como paginados, salvo los getById y Optional que se requiere un response por objeto y no una E.D como de tipo lista, stream, etc.\r\n"
        	      		+ "* Se Desarrollan Clases Específicas para el Manejo de Excepciones para cada Servicio , como también un manejador de excepciones y validaciones por campos de beans.\r\n"
        	      		+ "* Todas las funcionalidades tienen generación de logs en el Server para los errores y excepciones personalizadas\r\n"
        	      		+ "* Se incluye documentación completa de la Api con open-api para la visualización con swagger-ui, las anotaciones de open-api se aplican junto con los códigos de respuesta de tipo HTTP para cada función en los respectivos controllers.\r\n"
        	      		+ "* Se pone a disposición todos los recursos anteriores para productos y usuarios.\r\n"
        	      		+ "* Entre Otros.",
        	    "v3.0",
        	    "https://github.com/andresWeitzel/ApiRest_MicroFrontEnd_ProductosSupermercado/blob/master/LICENSE",
                new Contact("Andrés Weitzel","https://github.com/andresWeitzel/ApiRest_MicroFrontEnd_ProductosSupermercado" ,"andres96energy@hotmail.com"),
                "GNU v3.0",
                "https://github.com/andresWeitzel/ApiRest_MicroFrontEnd_ProductosSupermercado/blob/master/LICENSE",
                Collections.emptyList()
        );
    }
	
    
}


*/