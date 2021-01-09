package com.suyang.aop;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Enumeration;

/**
 * 实现Web层的日志切面
 */
@Slf4j
@Aspect
@Component
@Order(-5)
public class WebLogAspect {
    private ThreadLocal<Long> startTime = new ThreadLocal<>();

    /**
     * 定义一个切入点
     * <p>
     * ~ 第1个 * 代表任意修饰符及任意返回值.
     * ~ 第2个 * 任意包名
     * ~ 第3个 * 代表任意方法.
     * ~ .. 匹配任意数量的参数.
     */
    @Pointcut("execution(public * com.suyang.controller.*.*(..))")
    public void webLog() {
    }

    /**
     * 切入方法之前
     *
     * @param joinPoint
     */
    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) {
        startTime.set(System.currentTimeMillis());
        log.info("WebLogAspect.doBefore()");
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 记录下请求内容
        log.info("URL : " + request.getRequestURL().toString());
        log.info("HTTP_METHOD : " + request.getMethod());
        log.info("IP : " + request.getRemoteAddr());
        log.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        log.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
        Enumeration<String> enus = request.getParameterNames();
        while (enus.hasMoreElements()) {
            String paraName = enus.nextElement();
            log.info(paraName + ":" + request.getParameter(paraName));
        }
    }

    /**
     * 切入方法之后
     *
     * @param joinPoint
     */
    @AfterReturning("webLog()")
    public void doAfterReturning(JoinPoint joinPoint) {
        // 处理完请求，返回内容
        log.info("WebLogAspect.doAfterReturning()");
        log.info("耗时（毫秒） : " + (System.currentTimeMillis() - startTime.get()));
    }
}
