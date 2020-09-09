package com.my.admin.threadpoolexec;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TL1 {
    private static ThreadLocal<Integer> i = new ThreadLocal<>();
    private static final Logger LOGGER = LoggerFactory.getLogger(TL1.class);

    private static final ThreadPoolExecutor THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(
            1, 1, 1L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(1), new ThreadPoolExecutor.CallerRunsPolicy());

    private static final ThreadPoolExecutor THREAD_POOL_EXECUTOR_ALL = new ThreadPoolExecutor(
            5, 5, 1L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(50));

    public static void main(String[] args) {
        for (int j = 0; j < 10; j++) {
            THREAD_POOL_EXECUTOR_ALL.execute(new Runnable() {
                @Override
                public void run() {
                    for (int b = 0; b < 5; b++) {
                        THREAD_POOL_EXECUTOR.execute(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                LOGGER.info("hh");
                            }
                        });
                    }
                }
            });
        }
        System.out.println(123);
        THREAD_POOL_EXECUTOR_ALL.shutdown();
        THREAD_POOL_EXECUTOR.shutdown();
    }

    public void setI(int i1){
        i.set(i1);
    }
}
