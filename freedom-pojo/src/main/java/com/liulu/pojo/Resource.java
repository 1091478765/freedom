package com.liulu.pojo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by 刘璐 on 2018/7/30.
 */
@Configuration
@ConfigurationProperties(prefix="com.liulu")
@PropertySource(value="classpath:resource.properties")
public class Resource {
    private String name;

    private String gengder;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGengder() {
        return gengder;
    }

    public void setGengder(String gengder) {
        this.gengder = gengder;
    }
}
