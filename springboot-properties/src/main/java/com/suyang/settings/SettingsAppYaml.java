package com.suyang.settings;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@NoArgsConstructor
@Data
@Configuration
@ConfigurationProperties(prefix = "test")
public class SettingsAppYaml {
    private String name;
    private String gender;
}

