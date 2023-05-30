package edu.hm.cs.swe2.memberfees;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main entry point for the memberfees Microservice. Aka. "Microservice Mitgliedsbeiträge".
 * 
 * @author Jonas Buse
 */
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
