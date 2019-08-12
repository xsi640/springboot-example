package com.suyang.test;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestAutoConfiguration {
    @ConditionalOnMissingBean
    @Bean
    public TestProperties testProperties() {
        return new TestProperties();
    }

    @ConditionalOnMissingBean
    @Bean
    public TestRegistry testRegistry() {
        return new TestRegistry();
    }
}
