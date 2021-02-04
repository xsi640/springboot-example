package com.suyang.settings;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Data
@Configuration
@ConfigurationProperties(prefix = "test")
public class SettingsAppYaml {
    private String name;
    private String gender;
    private Map<String, String> maps;
    private String name2;
}

