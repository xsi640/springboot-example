package com.suyang.service;

/**
 * description: TokenService <br>
 * date: 2021/1/2 12:46 下午 <br>
 * author: suyang <br>
 * version: 1.0 <br>
 */
public interface TokenService {
    void set(String userId, String token);
    String get(String userId);
    void delete(String userId);
}
