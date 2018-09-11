package com.liulu.controller;

import com.liulu.annotation.LoginCheck;
import com.liulu.common.RsBody;
import com.liulu.redisUtils.RedisUtils;
import com.liulu.service.activeMqService.ActiveMqService;
import com.liulu.service.redisService.RedisService;
import com.liulu.service.test.impl.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

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
    private ActiveMqService activeMqService;

    @Autowired
    private RedisUtils redisUtils;

    @RequestMapping(value = "index")
    @LoginCheck
    public Object index(){
        RsBody rsBody = new RsBody();
        //redisService.insertKeyValue("123","321");
        redisUtils.set("123123","123r");
        return testService.index();
    }

    @RequestMapping(value = "sendMessage")
    public Object sendMessage(){
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1 = simpleDateFormat.format(date);
        activeMqService.sendMessage("mytest.queue",date1);
        return "";
    }

    @RequestMapping(value = "receiveQueue")
    public Object receiveQueue(){
        //activeMqService.receiveQueue("");
        return "";
    }

}
