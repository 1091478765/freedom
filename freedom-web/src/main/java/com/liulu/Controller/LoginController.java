package com.liulu.Controller;

import com.liulu.pojo.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by 刘璐 on 2018/7/30.
 */
@Controller
@RequestMapping(value = "web")
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);


    @Autowired
    private Resource resource;

    @RequestMapping(value = "index")
    public String index(ModelMap map){

        map.put("resource",resource);
        return "freemarker/index";
    }

}
