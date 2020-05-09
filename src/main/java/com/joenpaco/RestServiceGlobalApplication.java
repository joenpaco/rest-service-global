package com.joenpaco;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class RestServiceGlobalApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestServiceGlobalApplication.class, args);
	}

}
