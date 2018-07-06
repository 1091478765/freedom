package com.liulu.service.test.impl;

import org.springframework.context.annotation.ComponentScan;

/**
 * @Description:
 * @Copyright (c) by HomeFax.
 * @All right reserved.
 * @Create Date: 2018/7/6 10:07
 * @Create Author: nevermore
 * @File Name: TestService
 * @Last version: 2.1.0
 */
@ComponentScan(basePackages = {"com.liulu.service"})
public interface TestService {

    public String index();
}
