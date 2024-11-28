package com.example.farmInfo_be;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class FarmInfoBeApplication {

	public static void main(String[] args) {
		SpringApplication.run(FarmInfoBeApplication.class, args);
	}

}
