package com.seek.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@EnableMethodSecurity
@SpringBootApplication
@EnableJpaAuditing
public class SeekClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(SeekClientApplication.class, args);
	}

}
