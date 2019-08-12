package com.suyang.test;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("test")
public class TestProperties {
    private String name;
}
