package edu.hm.cs.swe2.memberfees.docs;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * Basic Swagger API Documentation class.
 * 
 * @author Jonas Buse (inspired by Stephan Schiffner)
 */
@Configuration
public class SpringFoxConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class)).paths(PathSelectors.any())
                .build().apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo("Membershipfees REST API", "For the Microservice \"Mitgliedsbeiträge\"", "v.0.1", "",
                new Contact("Jonas Buse", "", "buse@hm.edu"), "License of API", "API license URL",
                Collections.emptyList());
    }
}
