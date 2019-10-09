package edu.northeastern.cs5200;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


@SpringBootApplication
public class Cs5200Fall2019MoApplication
	extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder
		configure(SpringApplicationBuilder application) {
		return application.sources(
				Cs5200Fall2019MoApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(
				Cs5200Fall2019MoApplication.class, args);
	}
}


	
	//Cs5200Fall2019MoApplication.class