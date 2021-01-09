package com.suyang.aop;

import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LoggingAspect {

    @Around("@within(com.suyang.aop.Logging) || @annotation(com.suyang.aop.Logging)")
    public Object doLog(ProceedingJoinPoint point) throws Throwable {
        log.info("entering {}", point.getSignature());
        try {
            return point.proceed();
        } finally {
            log.info("exiting {}", point.getSignature());
        }
    }

}
