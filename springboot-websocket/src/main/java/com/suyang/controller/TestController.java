package com.suyang.controller;

import com.suyang.domain.MessagePackage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;

@Slf4j
@Controller
public class TestController {
    @GetMapping("/")
    public String index() {
        return "/index";
    }

    @MessageMapping("/send")
    @SendTo("/topic/msg")
    public MessagePackage send(MessagePackage message) {
        log.info("receive msg:" + message.getMessage());
        return new MessagePackage(message.getMessage() + "...ok", new Date());
    }
}
