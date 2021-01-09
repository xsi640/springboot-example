package com.suyang.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class HelloController {
    @RequestMapping("/hello")
    public String hello(String name, Map<String, String> map) {
        map.put("name", name);
        return "/hello";
    }
}
