package com.liulu.service.test;

import com.liulu.dao.UserMapper;
import com.liulu.pojo.User;
import com.liulu.service.test.impl.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 刘璐 on 2018/7/5.
 */
@Service
public class TestServiceImpl implements TestService{

    @Autowired
    private UserMapper userMapper;

    @Override
    public String index(){
        List<User> list = userMapper.selectAll();
        System.out.print(list.size());
        return "service";
    }
}
