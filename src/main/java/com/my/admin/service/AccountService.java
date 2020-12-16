package com.my.admin.service;

import com.github.AopLog;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
@AopLog
public class AccountService {
    private String name;
    public AccountService() {
        this.name = "5";
    }

    public void a(){
        System.out.println("a");
    }

    @Bean("ab")
    public String ab(){
        System.out.println("ab");
        System.out.println(name);
        return "bbc";
    }
    @Bean("abc")
    public String abc(@Autowired String abr){
        System.out.println("abc");
        System.out.println(abr);
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
