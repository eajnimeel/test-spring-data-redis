package com.example.springdataredis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.stereotype.Service;

@Service
public class CacheServiceImpl implements CacheService {

    private RedisTemplate redisTemplate;
    private ValueOperations valueOperations;

    private Jackson2JsonRedisSerializer<Object> defalutSerializer = new Jackson2JsonRedisSerializer<Object>(Object.class);

    @Autowired
    public CacheServiceImpl(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.valueOperations = redisTemplate.opsForValue();
    }

    @Override
    public void put(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    @Override
    public Object get(String key) {
        return valueOperations.get(key);
    }
}
