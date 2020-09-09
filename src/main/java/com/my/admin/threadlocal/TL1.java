package com.my.admin.threadlocal;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

public class TL1 {
//    private static ThreadLocal<Integer> i = new ThreadLocal<>();
    private static final Logger LOGGER = LoggerFactory.getLogger(TL1.class);

    private static final ThreadPoolExecutor THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(
            5, 5, 1L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(5), new ThreadPoolExecutor.CallerRunsPolicy());

    public static void main(String[] args) throws InterruptedException {
        ThreadLocal<Integer> i = new ThreadLocal<>();
        i.set(4);
        THREAD_POOL_EXECUTOR.execute(new Runnable() {
            @Override
            public void run() {
//                i.set(5);
                System.out.println(i.get());
            }
        });
        Thread.sleep(1000);
        System.out.println(i.get());
        THREAD_POOL_EXECUTOR.execute(new Runnable() {
            @Override
            public void run() {
                i.set(8);
                System.out.println(i.get());
            }
        });
        Thread.sleep(1000);
        System.out.println(i.get());
        THREAD_POOL_EXECUTOR.execute(new Runnable() {
            @Override
            public void run() {
                i.set(10);
                System.out.println(i.get());
            }
        });
        Thread.sleep(1000);
        System.out.println(i.get());
    }

    public void setI(int i1){
//        i.set(i1);
    }
}
