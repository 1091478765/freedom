package com.liulu.controller;

import com.liulu.common.RsBody;
import com.liulu.redisUtils.RedisUtils;
import com.liulu.service.redisService.RedisService;
import com.liulu.service.test.impl.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 刘璐 on 2018/7/5.
 */

@RestController
public class TestControoler {


    @Autowired
    private TestService testService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private RedisUtils redisUtils;

    @RequestMapping(value = "index")
    public Object index(){
        RsBody rsBody = new RsBody();
        //redisService.insertKeyValue("123","321");
        redisUtils.set("123123","123r");
        return testService.index();
    }
}
