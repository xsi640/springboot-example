package com.suyang.service;

import com.suyang.controller.request.UserRegisterRequest;
import com.suyang.controller.request.UserUpdateRequest;
import com.suyang.entity.User;

import java.util.List;

/**
 * description: UserService <br>
 * date: 2021/1/2 1:33 下午 <br>
 * author: suyang <br>
 * version: 1.0 <br>
 */
public interface UserService {
    User save(UserRegisterRequest user);

    User findByUsername(String username);

    User update(UserUpdateRequest user);

    void delete(String username);

    boolean check(String inputPassword, String password);

    List<User> findAll();
}
