package edu.hm.cs.swe2.eventPictures.docs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

/**
 * Basic Swagger Configuration to document the API
 *
 * @author Felix Pollok
 * @project se2_21_gruppe_05
 */
@Configuration
public class SpringFoxConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Event Fotos REST API",
                "The microservice 'Event Pictures' for the Footballclub manager system",
                "API 1.1",
                "Terms of service",
                new Contact("Felix Pollok", "cs.hm.edu", "fpollok@hm.edu"),
                "License of API", "API license URL", Collections.emptyList());
    }
}
