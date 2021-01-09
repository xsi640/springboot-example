package com.suyang.mq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
public class Consumer {

    static volatile long i = 0;

    @KafkaListener(topics = "test1", groupId = "group_id")
    public void consume(String message) throws IOException {
//        i++;
//        if (i == 5) {
//            try {
//                Thread.sleep(1000000000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
        log.info("consume " + i + " message:" + message);
    }
}
