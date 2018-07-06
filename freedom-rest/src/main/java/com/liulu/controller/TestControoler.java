package com.liulu.controller;

import com.liulu.service.test.impl.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 刘璐 on 2018/7/5.
 */

@RestController
public class TestControoler {


    @Autowired
    private TestService testService;

    @RequestMapping(value = "index")
    public String index(){
        return testService.index();
    }
}
