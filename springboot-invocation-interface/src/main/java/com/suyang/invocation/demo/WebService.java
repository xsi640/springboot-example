package com.suyang.invocation.demo;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface WebService {
    @AliasFor("value")
    String name() default "";

    @AliasFor("name")
    String value() default "";
}
