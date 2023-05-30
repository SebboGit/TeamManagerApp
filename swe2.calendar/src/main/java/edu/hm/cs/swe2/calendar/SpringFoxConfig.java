package edu.hm.cs.swe2.calendar;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

/**
 * Swagger configuration
 *
 * @author Sebastian Theimer
 */
@Configuration
public class SpringFoxConfig {

  public static final String TAG_CREATE_EVENT = "Create Events";
  public static final String TAG_RETRIEVE_EVENT = "Retrieve Events";
  public static final String TAG_DELETE_EVENT = "Delete Events";
  public static final String TAG_UPDATE_EVENT = "Update Events";

  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.any())
        .paths(PathSelectors.any())
        .build()
        .apiInfo(apiInfo());
  }

  private ApiInfo apiInfo() {
    return new ApiInfo(
        "Calendar REST API",
        "For the Microservice Calendar",
        "v.0.1",
        "",
        new Contact("Sebastian Theimer", "", "stheimer@hm.edu"),
        "License of API",
        "API license URL",
        Collections.emptyList());
  }
}
