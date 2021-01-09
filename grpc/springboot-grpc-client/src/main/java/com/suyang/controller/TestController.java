package com.suyang.controller;

import com.suyang.grpc.GrpcClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    private GrpcClientService grpcClientService;

    @RequestMapping(path = "/")
    public String printMessage(@RequestParam(defaultValue = "Michael") final String name) {
        return this.grpcClientService.sendMessage(name);
    }
}
