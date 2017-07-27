package edu.jproyo.dojos.contributorstest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

/**
 * The Class Application.
 */
@SpringBootApplication
@EnableWebSecurity
public class Application {
	
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
}
