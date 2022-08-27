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
        		"ApiRest ElectroThingsV1 SpringBoot MongoDB.",
        		"* Api Rest para la Aplicación Electro Things acerca de Productos Electrónicos con Spring Boot, Spring MVC, Spring Security, JWT, Spring Data MongoDB, SpringDoc-OpenApi, Swagger UI, Api Highchart, Maven, Lombok, Log4j, Git, MongoDBCompass, MongoDB y Otras Tecnologías.\r\n"
        		+ "* Se Desarrollan Clases Específicas para el Manejo de Excepciones para cada Servicio , como también un manejador de excepciones y validaciones por campos de beans.\r\n"
        		+ "* Todas las funcionalidades tienen generación de logs en el Server para los errores y excepciones personalizadas.\r\n"
        		+ "* Se incluye documentación completa de la Api con open-api para la visualización con swagger-ui, las anotaciones de open-api se aplican junto con los códigos de \r\n"
        		+ " respuesta de tipo HTTP para cada función en los respectivos controllers.\r\n"
        		+ "* Se desarrolla toda la funcionalidad para las Operaciones CRUD, como así también paginados y funcionalidades para uso de filtros de búsqueda de productos desde el frontend. \r\n"
        		+ "* Se separa la capa de seguridad para la autenticación , implementando Spring Security y JWT. Además de realizar las operaciones CRUD para usuarios se aplica login y signin para la capa de presentación.\r\n"
        		+ "* También se desarrollan los métodos de búsquedas independientes de tipo Like para todos los campos, tanto de usuarios como productos.\r\n"
        		+ "* Los objetos de tipo getBy se manipulan como paginados, salvo los getById y Optional que se requiere un response por objeto y no una E.D como de tipo lista, stream, etc.\r\n"
        		+ "* Entre Otros.\r\n"
        		+ "* Se pone a disposición todos los recursos anteriores para productos y usuarios.\r\n"
        		+ "\r\n"
        		+ "</br>\r\n"
        		+ "\r\n"
        		+ "* Repositorio AppTiendaElectronica Original : https://github.com/andresWeitzel/AppTiendaElectronica_Angular_Bootstrap_SpringBoot_MongoDB \r\n"
        		+ "* Repositorio AppTiendaElectronica `(migrado a..)`: https://gitlab.com/andres-weitzel/AppTiendaElectronica_Angular_Bootstrap_SpringBoot_MongoDB\r\n"
        		+ "* Repositorio db_ElectroThings : https://github.com/andresWeitzel/db_ElectroThings_MongoDB",
        	    "v3.0",
        	    "https://github.com/andresWeitzel/ApiRest_ElectroThingsV1_SpringBoot_MongoDB/blob/master/LICENSE",
                new Contact("Andrés Weitzel","https://github.com/andresWeitzel/ApiRest_ElectroThingsV1_SpringBoot_MongoDB" ,"andres96energy@hotmail.com"),
                "GNU v3.0",
                "https://github.com/andresWeitzel/ApiRest_ElectroThingsV1_SpringBoot_MongoDB/blob/master/LICENSE",
                Collections.emptyList()
        );
    }

	
    
}