package com.suyang.mq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

//@Slf4j
//@Service
//public class Consumer1 {
//
//    @KafkaListener(topics = "test1", groupId = "group_id")
//    public void consume(String message) throws IOException {
//        Consumer.i++;
//        log.info("consume " + Consumer.i + " message:" + message);
//    }
//}
