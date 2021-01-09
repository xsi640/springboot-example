package com.suyang.runner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Slf4j
@Order(3)
@Component
public class Runner3 implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        log.info("runner3 start. order = 3");
    }
}
