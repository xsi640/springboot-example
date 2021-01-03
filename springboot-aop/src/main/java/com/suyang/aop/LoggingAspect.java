package com.haohan.zero.core.aop;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Log4j2
@Aspect
@Component
public class LoggingAspect {

    @Around("@within(com.haohan.zero.core.aop.Logging) || @annotation(com.haohan.zero.core.aop.Logging)")
    public Object doLog(ProceedingJoinPoint point) throws Throwable {
        log.info("entering {}", point.getSignature());
        try {
            return point.proceed();
        } finally {
            log.info("exiting {}", point.getSignature());
        }
    }

}
