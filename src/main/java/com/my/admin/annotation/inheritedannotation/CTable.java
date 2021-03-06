package com.my.admin.annotation.inheritedannotation;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface CTable {
    String name() default "";
}
