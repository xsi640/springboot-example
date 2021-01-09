package com.suyang.controller;

import com.suyang.Constants;
import com.suyang.controller.request.LoginRequest;
import com.suyang.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * description: AuthController <br>
 * date: 2021/1/2 1:28 下午 <br>
 * author: suyang <br>
 * version: 1.0 <br>
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody LoginRequest request) {
        String token = authService.getToken(request.getUsername(), request.getPassword());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(Constants.TOKEN_HEADER, token);
        return new ResponseEntity<>(httpHeaders, HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout() {
        authService.deleteToken();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
