package com.suyang.service.impl;

import com.suyang.CurrentUserUtils;
import com.suyang.TokenUtils;
import com.suyang.dto.JwtUser;
import com.suyang.entity.User;
import com.suyang.service.AuthService;
import com.suyang.service.TokenService;
import com.suyang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * description: AuthServiceImpl <br>
 * date: 2021/1/2 1:30 下午 <br>
 * author: suyang <br>
 * version: 1.0 <br>
 */
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserService userService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private CurrentUserUtils currentUserUtils;

    @Override
    public String getToken(String username, String password) {
        User user = userService.findByUsername(username);
        if (!userService.check(password, user.getPassword())) {
            throw new BadCredentialsException("The user name or password is not correct.");
        }
        JwtUser jwtUser = new JwtUser(user);
        if (!jwtUser.isEnabled()) {
            throw new BadCredentialsException("User is forbidden to log in");
        }
        List<String> authorities = jwtUser.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        String token = TokenUtils.createToken(user.getUserName(), user.getId().toString(), authorities);
        tokenService.set(user.getId().toString(), token);
        return token;
    }

    @Override
    public void deleteToken() {
        tokenService.delete(currentUserUtils.getCurrentUser().getId().toString());
    }
}
