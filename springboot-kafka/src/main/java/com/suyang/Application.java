package com.suyang;

import com.suyang.mq.Producer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@Slf4j
@SpringBootApplication
public class Application {

    @Autowired
    private Producer producer;

    @Bean
    public CommandLineRunner run() {
        return args -> {
            for (int i = 0; i < 100; i++) {
//                new Thread(() -> {
//                    for (int k = 0; k < 100000; k++) {
                producer.sendMessage("ThreadId:" + Thread.currentThread().getId() + " time:" + new Date());
//                    }
//                    log.info("ThreadId:" + Thread.currentThread().getId() + " send successed.");
//                }).start();
            }
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
