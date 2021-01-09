package com.suyang.controller.request;

import lombok.Data;

/**
 * description: LoginRequest <br>
 * date: 2021/1/2 2:26 下午 <br>
 * author: suyang <br>
 * version: 1.0 <br>
 */
@Data
public class LoginRequest {
    private String username;
    private String password;
}
