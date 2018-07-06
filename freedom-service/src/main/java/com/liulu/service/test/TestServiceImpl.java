package com.liulu.service.test;

import com.liulu.service.test.impl.TestService;
import org.springframework.stereotype.Service;

/**
 * Created by 刘璐 on 2018/7/5.
 */
@Service
public class TestServiceImpl implements TestService{

    @Override
    public String index(){
        return "service";
    }
}
