package com.liulu.controller;

import com.liulu.service.test.TestServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 刘璐 on 2018/7/5.
 */

@RestController
public class TestControoler {


    @Autowired
    private TestServiceImpl testService;

    @RequestMapping(value = "index")
    public String index(){
        return testService.index();
    }
}