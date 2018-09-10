package com.liulu.config;

import com.liulu.interceptor.RequestInterceptor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by 刘璐 on 2018/7/26.
 */
@Configuration
@ComponentScan(value = "com.liulu")
public class RestConfig extends WebMvcConfigurerAdapter{

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new RequestInterceptor());
        super.addInterceptors(registry);
    }
}
