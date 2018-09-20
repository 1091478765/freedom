package com.liulu;

import com.liulu.filter.HttpFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.liulu.*"})
@MapperScan(basePackages = "com.liulu.dao")
public class FreedomRestApplication {

	public static void main(String[] args) {

		SpringApplication.run(FreedomRestApplication.class, args);
		//SpringApplication.run(clazzs, args);
	}

	@Bean
	public FilterRegistrationBean httpFilter(){
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		filterRegistrationBean.setFilter(new HttpFilter());
		return filterRegistrationBean;
	}
}
