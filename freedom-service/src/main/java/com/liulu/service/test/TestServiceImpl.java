package com.liulu.service.test;

import com.github.pagehelper.PageHelper;
import com.liulu.dao.UserMapper;
import com.liulu.pojo.User;
import com.liulu.service.test.impl.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.UUID;

/**
 * Created by 刘璐 on 2018/7/5.
 */
@Service
public class TestServiceImpl implements TestService{

    @Autowired
    private UserMapper userMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<User> index(){
        PageHelper pageHelper = new PageHelper();
        PageHelper.startPage(1, 10);
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        //criteria.andEqualTo(1);
        List<User> list = userMapper.selectByExample(example);
        User user = new User();
        user.setUuid(UUID.randomUUID().toString());
        user.setName("liulu");
        userMapper.insert(user);
       // int i = 1/0;

        System.out.print(list.size());
        return list;
    }
}
