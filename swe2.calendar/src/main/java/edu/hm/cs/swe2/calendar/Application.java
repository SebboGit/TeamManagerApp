package edu.hm.cs.swe2.calendar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

/**
 * Spring Boot calendar microservice
 *
 * @author Sebastian Theimer
 */
@ComponentScan({"edu.hm.cs.swe2.calendar.service", "edu.hm.cs.swe2.calendar.controllers"})
@SpringBootApplication
public class Application extends SpringBootServletInitializer {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
