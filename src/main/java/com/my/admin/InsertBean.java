package com.my.admin;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;


public class InsertBean {

    @Bean("bbccc")
    public String bbccc(){
        System.out.println("bbccc");
        return "bbccc";
    }

    @PostConstruct
    public void init() {
        System.out.println("BBBBBBBBBCCCCCCCCC");
    }
}
