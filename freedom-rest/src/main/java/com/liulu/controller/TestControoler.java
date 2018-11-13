package com.liulu.controller;

import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.data.PictureRenderData;
import com.deepoove.poi.util.BytePictureUtils;
import com.liulu.SpringUtil.SpringUtils;
import com.liulu.annotation.LoginCheck;
import com.liulu.common.RsBody;
import com.liulu.pojo.User;
import com.liulu.redisUtils.RedisUtils;
import com.liulu.service.activeMqService.ActiveMqService;
import com.liulu.service.redisService.RedisService;
import com.liulu.service.test.impl.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by 刘璐 on 2018/7/5.
 */

@RestController
@Slf4j
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
        //redisUtils.set("123123","123r");
        //return testService.index();
        return null;
    }

    @RequestMapping(value = "login")
    public Object login(){
        RsBody rsBody = new RsBody();
        //redisService.insertKeyValue("123","321");
        User user = new User();
        user.setName("liulu");
        user.setPassword("123456");
        SpringUtils.getRequest().getSession().setAttribute("user",user);
        //redisUtils.set("123123","123r");
        //return testService.index();
        return "";
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




    @RequestMapping(value = "receiveQueue")
    public Object test(){
        //activeMqService.receiveQueue("");
        List<User> users = Arrays.asList();
        Map<String,List<User>> map = users.stream().collect(Collectors.groupingBy(user -> user.getGender()));
        return "";
    }

    public static void main(String[] args) throws Exception{
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("userName","刘璐");
		map.put("companyName","红星美凯龙");
		map.put("idNum","340827199302235810");
		Calendar calendar = Calendar.getInstance();
		map.put("year", calendar.get(Calendar.YEAR) + "");
		map.put("month", (calendar.get(Calendar.MONTH) + 101 + "").substring(1));
		map.put("day", (calendar.get(Calendar.DAY_OF_MONTH) + 100 + "").substring(1));
        byte[] localByteArray = BytePictureUtils.getLocalByteArray(new File("C:\\Users\\lenovo\\Desktop\\front.png"));
        map.put("localBytePicture", new PictureRenderData(100, 50, ".png", localByteArray));
        XWPFTemplate template = XWPFTemplate.compile("C:\\Users\\lenovo\\Desktop\\授权书.docx").render(map);
        FileOutputStream out = new FileOutputStream("out_template1.docx");
        template.write(out);
        out.flush();
        out.close();
        template.close();
    }

}
