package com.suyang.invocation.demo;

import org.springframework.core.env.Environment;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class WebServiceInvokerProxy implements InvocationHandler {

    public WebServiceInvokerProxy(Class<?> objectType, Environment environment) {
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return "invoke " + method.getName();
    }
}
