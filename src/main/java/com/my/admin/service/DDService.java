package com.my.admin.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Date;

public class DDService {

    @Async
    public void async(){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("异步方法" + new Date());
    }

    @Transactional
    public void noAsync(){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("方法完成" + new Date());
    }

    public void dd(){

    }
}
