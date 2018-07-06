package com.liulu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.liulu")
public class FreedomServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FreedomServiceApplication.class, args);
	}
}
