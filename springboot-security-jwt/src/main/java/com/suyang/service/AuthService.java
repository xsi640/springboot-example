package com.suyang.service;

/**
 * description: AuthService <br>
 * date: 2021/1/2 1:29 下午 <br>
 * author: suyang <br>
 * version: 1.0 <br>
 */
public interface AuthService {
    String getToken(String username, String password);

    void deleteToken();
}
