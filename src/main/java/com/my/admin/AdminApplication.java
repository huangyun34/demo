package com.my.admin;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.LinkedHashSet;

//@EnableAutoConfiguration
//@SpringBootApplication
@EnableRabbit
@ComponentScan("com.my")
//监控
//@EnableAdminServer
@EnableAsync
public class AdminApplication {
    public static void main(String[] args){
//        Class<?>[] c = {AdminApplication.class, JobApplication.class};
//        String[] s = {};
//        SpringApplication.run(c, s);
        SpringApplication springApplication = new SpringApplication(AdminApplication.class, JobApplication.class);
//        springApplication.setLogStartupInfo(false);
        String[] args1 = {"123"};
        springApplication.run(args1);
    }
}
