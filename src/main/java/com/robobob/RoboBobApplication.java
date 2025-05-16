package com.robobob;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class RoboBobApplication {

	public static void main(String[] args) {
		SpringApplication.run(RoboBobApplication.class, args);
	}

}
