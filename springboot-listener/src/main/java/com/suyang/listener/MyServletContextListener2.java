package com.suyang.listener;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

@Slf4j
public class MyServletContextListener2 implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        log.info("ServletContext 初始化 from bean");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        log.info("ServletContext 销毁 from bean");
    }
}
