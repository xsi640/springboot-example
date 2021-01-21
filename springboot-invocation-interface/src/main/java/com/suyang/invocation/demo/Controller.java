package com.suyang.invocation.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @Autowired
    private WebServiceDemo webServiceDemo;

    @GetMapping
    public String get() {
        return webServiceDemo.get();
    }
}
