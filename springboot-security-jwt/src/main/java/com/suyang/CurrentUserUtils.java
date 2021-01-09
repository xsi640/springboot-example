package com.suyang;

import com.suyang.entity.User;
import com.suyang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * description: CurrentUserUtils <br>
 * date: 2021/1/2 1:36 下午 <br>
 * author: suyang <br>
 * version: 1.0 <br>
 */
@Component
public class CurrentUserUtils {

    @Autowired
    private UserService userService;

    public User getCurrentUser() {
        return userService.findByUsername(getCurrentUserName());
    }

    private String getCurrentUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() != null) {
            return (String) authentication.getPrincipal();
        }
        return null;
    }
}
