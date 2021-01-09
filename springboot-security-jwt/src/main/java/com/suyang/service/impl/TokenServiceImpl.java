package com.suyang.service.impl;

import com.suyang.service.TokenService;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * description: TokenServiceImpl <br>
 * date: 2021/1/2 12:48 下午 <br>
 * author: suyang <br>
 * version: 1.0 <br>
 */
@Service
public class TokenServiceImpl implements TokenService {

    private static ConcurrentMap<String, String> cached = new ConcurrentHashMap<>();

    @Override
    public void set(String userId, String token) {
        cached.put(userId, token);
    }

    @Override
    public String get(String userId) {
        return cached.get(userId);
    }

    @Override
    public void delete(String userId) {
        cached.remove(userId);
    }
}
