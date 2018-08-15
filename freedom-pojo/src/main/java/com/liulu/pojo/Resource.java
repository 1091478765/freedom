package com.liulu.pojo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.ResourceBundle;

/**
 * Created by 刘璐 on 2018/7/30.
 */
@Component
@Configuration
@ConfigurationProperties(prefix="com.liulu")
@PropertySource(value="classpath:resource.properties")
public class Resource {


    private static String name;

    private static String gengder;

    static {
        ResourceBundle sysProperties = ResourceBundle.getBundle("resource");
        try{
            name = sysProperties.getString("name");
            gengder = sysProperties.getString("gengder");

        } catch(Exception e){

        }
    }



}
