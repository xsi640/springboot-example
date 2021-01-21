package com.suyang.controller.request;

import com.suyang.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * description: UserRegisterRequest <br>
 * date: 2021/1/2 1:54 下午 <br>
 * author: suyang <br>
 * version: 1.0 <br>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterRequest {
    @NotBlank
    private String userName;
    @NotBlank
    private String password;

    public User toUser() {
        User result = new User();
        result.setUserName(userName);
        result.setEnabled(true);
        return result;
    }
}
