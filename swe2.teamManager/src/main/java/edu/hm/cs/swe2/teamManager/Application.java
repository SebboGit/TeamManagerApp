package edu.hm.cs.swe2.teamManager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * Main entry point for the microservice "teamManager" also know as "Microservice Mannschaften"
 *
 * @author Michael Fortenbacher
 */
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
