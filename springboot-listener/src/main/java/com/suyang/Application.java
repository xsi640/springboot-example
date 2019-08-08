package com.suyang;

import com.suyang.listener.MyServletContextListener2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;

import java.util.EventListener;

/**
 * 支持@WebListener注解
 */
@ServletComponentScan
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public ServletListenerRegistrationBean<EventListener> getListener() {
        ServletListenerRegistrationBean<EventListener> registrations = new ServletListenerRegistrationBean<EventListener>();
        registrations.setListener(new MyServletContextListener2());
        return registrations;
    }
}
