package com.liulu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@PropertySource(value = "classpath:application.properties")
@MapperScan(basePackages = "com.liulu.dao")
public class FreedomDaoApplication {

	public static void main(String[] args) {
		SpringApplication.run(FreedomDaoApplication.class, args);
	}
}
