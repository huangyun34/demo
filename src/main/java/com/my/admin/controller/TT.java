package com.my.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface TT {
    @AliasFor(attribute = "hh")
    String value() default "";

    @AliasFor(attribute = "value")
    String hh() default "";

    @AliasFor("hh")
    String hhh() default "";

}
