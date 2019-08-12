package com.suyang;

import com.suyang.test.TestNameConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NameConfiguration {
    @Bean
    public TestNameConfigurer testNameConfigurer() {
        return registry -> registry.addName("suyang");
    }
}
