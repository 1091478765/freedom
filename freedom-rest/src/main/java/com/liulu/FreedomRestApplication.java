package com.liulu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.liulu.*"})

public class FreedomRestApplication {

	public static void main(String[] args) {

		/*Class[] clazzs = new Class[3];
		clazzs[0] = FreedomRestApplication.class;
		clazzs[1] = FreedomServiceApplication.class;
		clazzs[2] = FreedomDaoApplication.class;
*/
		SpringApplication.run(FreedomRestApplication.class, args);
		//SpringApplication.run(clazzs, args);
	}
}
