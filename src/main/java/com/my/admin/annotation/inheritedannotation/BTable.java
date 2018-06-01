package com.my.admin.annotation.inheritedannotation;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface BTable {
    String name() default "";
}
