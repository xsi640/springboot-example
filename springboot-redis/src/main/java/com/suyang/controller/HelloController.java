package com.suyang.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class HelloController {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @GetMapping("/test")
    public String test() {
        // add key value
        redisTemplate.opsForValue().set("test", "the test value in redis.");
        return "saved";
    }

    @GetMapping("/test2")
    public String test2() {
        // get value
        return redisTemplate.opsForValue().get("test");
    }

    @GetMapping("/test3")
    @Cacheable(value = "test3_cache", keyGenerator = "wiselyKeyGenerator")
    public String test3() {
        log.info("无缓存的时候调用这里");
        return "test3 cache";
    }
}
