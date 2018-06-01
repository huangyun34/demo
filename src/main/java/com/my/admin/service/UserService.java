package com.my.admin.service;

import com.google.common.util.concurrent.ListenableFutureTask;
import com.my.admin.annotation.OrderByAutowired;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.concurrent.Future;

@Service
public class UserService {

    @Async
    public void async(){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("异步方法" + new Date());
    }

    public void noAsync(){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("方法完成" + new Date());
    }

    /**
     * 能获得返回值
     * @return
     */
    @Async
    public Future<String> asyncString(){
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println("没有断掉也");
        long now = new Date().getTime();
        Date cu = new Date();
        while ((cu.getTime()/1000 - now/1000) < 4){
            cu = new Date();
        }
        System.out.println("chenggin");
        return new AsyncResult("nihao");
    }

    /**
     * 不能获得返回值
     * @return
     */
    @Async
    public String asyncStrings(){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "nihao";
    }
}
