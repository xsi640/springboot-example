package com.suyang.settings;

import com.suyang.YamlPropertyLoaderFactory;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@NoArgsConstructor
@Data
@Configuration
@PropertySource(value = "classpath:settings.yml", factory = YamlPropertyLoaderFactory.class)
@ConfigurationProperties(prefix = "settings")
public class SettingsYaml {
    private String name;
    private int age;
}
