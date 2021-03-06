package com.suyang.controller;

import com.suyang.aop.Logging;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Logging
@RestController
public class HelloController {
    @RequestMapping("/")
    public String hello(@RequestParam(name = "name", required = false) String name) {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Hello, " + name;
    }
}
