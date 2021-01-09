package com.suyang.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * description: UserUpdateRequest <br>
 * date: 2021/1/2 1:56 下午 <br>
 * author: suyang <br>
 * version: 1.0 <br>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateRequest {
    private String userName;
    private String password;
    private boolean enabled;
}
