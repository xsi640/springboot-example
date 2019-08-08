package com.suyang.mq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Producer {
    @Autowired
    private AmqpTemplate amqpTemplate;

    public void send(String s) {
        log.info("Producer:" + s);
        amqpTemplate.convertAndSend("hello", s);
    }
}
