package com.suyang.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;

@Slf4j
@Configuration
@EnableScheduling
public class ScheduleTest {
    //5秒執行一次
    @Scheduled(cron = "0/5 * * * * ?")
    public void test() {
        log.info("test() >>>>>>>>> time:" + new Date());
    }

    @Scheduled(cron = "${schedule.cron}")
    public void test2() {
        log.info("test2() >>>>>>>>> time:" + new Date());
    }
}
