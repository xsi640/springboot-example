package com.suyang.listener;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@Slf4j
@WebListener
public class MyServletContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        log.info("ServletContext 初始化 from annotation");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        log.info("ServletContext 销毁 from annotation");
    }
}
