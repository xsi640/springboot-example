package com.suyang.controller;

import com.suyang.settings.SettingsAppYaml;
import com.suyang.settings.SettingsProp;
import com.suyang.settings.SettingsYaml;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @Autowired
    private SettingsAppYaml settingsAppYaml;
    @Autowired
    private SettingsProp settingsProp;
    @Autowired
    private SettingsYaml settingsYaml;

    @GetMapping("/test")
    public String test() {
        return "name:" + settingsAppYaml.getName() + " gender:" + settingsAppYaml.getGender() + " " + settingsAppYaml.getName2();
    }

    @GetMapping("/test2")
    public String test2() {
        return "name:" + settingsProp.getName() + " gender:" + settingsProp.getAge();
    }

    @GetMapping("/test3")
    public String test3() {
        return "name:" + settingsYaml.getName() + " gender:" + settingsYaml.getAge();
    }

    @GetMapping("/test4")
    public String test4() {
        StringBuilder builder = new StringBuilder();
        for (String s : settingsAppYaml.getMaps().keySet()) {
            builder.append(s).append(":").append(settingsAppYaml.getMaps().get(s)).append("/n");
        }
        return builder.toString();
    }
}
