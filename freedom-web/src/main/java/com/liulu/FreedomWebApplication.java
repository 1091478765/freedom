package com.liulu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.liulu.*"})
@MapperScan(basePackages = "com.liulu.dao")
public class FreedomWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(FreedomWebApplication.class, args);
	}
}
