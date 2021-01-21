package com.suyang.invocation.demo;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import java.lang.reflect.Proxy;

public class WebServiceBeanFactory implements FactoryBean<Object> {
    private ClassLoader classLoader;
    private Class<?> objectType;

    @Autowired
    private Environment environment;

    @Override
    public Object getObject() {
        return Proxy.newProxyInstance(classLoader,
                new Class[]{objectType},
                new WebServiceInvokerProxy(objectType, environment));
    }

    @Override
    public Class<?> getObjectType() {
        return this.objectType;
    }

    public void setClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    public void setObjectType(Class<?> objectType) {
        this.objectType = objectType;
    }
}
