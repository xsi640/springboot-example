package com.suyang.controller;

import com.suyang.controller.request.UserRegisterRequest;
import com.suyang.controller.request.UserUpdateRequest;
import com.suyang.entity.User;
import com.suyang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * description: UserController <br>
 * date: 2021/1/2 2:18 下午 <br>
 * author: suyang <br>
 * version: 1.0 <br>
 */
@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public void save(@RequestBody UserRegisterRequest request) {
        userService.save(request);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_MANAGER','ROLE_ADMIN')")
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    @PutMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public void update(@RequestBody UserUpdateRequest request) {
        userService.update(request);
    }

    @DeleteMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public void delete(@RequestParam("username") String username) {
        userService.delete(username);
    }
}
