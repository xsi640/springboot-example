package com.suyang.settings;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@NoArgsConstructor
@Data
@Configuration
@PropertySource("classpath:settings.properties")
@ConfigurationProperties(prefix = "prop")
public class SettingsProp {
    private String name;
    private int age;
}
