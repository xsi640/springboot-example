package com.suyang.runner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Slf4j
@Order(4)
@Component
public class Runner2 implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        log.info("runner2 start. order = 4");
    }
}
