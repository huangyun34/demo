package com.my.admin.annotation.importannotation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(DemoService.class)
public class DemoConfig {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext("com.my.admin.annotation.importannotation");
//        DemoService ds = context.getBean(DemoService.class);
        DemoService ds = (DemoService)context.getBean("com.my.admin.annotation.importannotation.DemoService");
        ds.doSomeThing();
    }
}
