package com.liulu.service.test.impl;

import com.liulu.pojo.User;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @Copyright (c) by HomeFax.
 * @All right reserved.
 * @Create Date: 2018/7/6 10:07
 * @Create Author: nevermore
 * @File Name: TestService
 * @Last version: 2.1.0
 */
public interface TestService {

    public List<User> index();
}
