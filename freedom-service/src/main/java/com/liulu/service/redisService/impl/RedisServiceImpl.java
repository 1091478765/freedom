package com.liulu.service.redisService.impl;

import com.liulu.service.redisService.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by 刘璐 on 2018/7/24.
 */
@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public void insertKeyValue(String key, String value) {
        redisTemplate.opsForValue().set(key,value);
    }
}
