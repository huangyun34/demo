package com.my.admin.leetcode;

import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MultThread {

    private static final ThreadPoolExecutor THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(5,5,0l, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(1));
    private static volatile int count = 1;
    private static boolean single = true;
    private static Object o = new Object();
    public static void main(String[] args) {

        R1 r1 = new R1();
        R2 r2 = new R2();
        R3 r3 = new R3();
        Thread thread = new Thread(r1);
        Thread thread2 = new Thread(r2);
        FutureTask futureTask = new FutureTask(r3);
        Thread thread3 = new Thread(futureTask);
        thread.start();
        thread2.start();
        thread3.start();
//        Executors.newCachedThreadPool()
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
//        scheduledExecutorService.scheduleAtFixedRate()
//        THREAD_POOL_EXECUTOR.execute(r1);
//        THREAD_POOL_EXECUTOR.execute(r2);
//        THREAD_POOL_EXECUTOR.shutdown();
    }

    private static class R1 implements Runnable {

        @Override
        public void run() {
            synchronized (o) {
                System.out.println("====");
                for (int i = 1; i <= 10; i++) {
                    int hh = i * 2;
                    while (single) {
                        try {
                            o.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println(hh);
//                    count = count + 1;
                    single = true;
                    o.notifyAll();
                }
//                while (count == 1) {
//                    try {
//                        System.out.println("-=-=-=-=-" + j);
//                        o.wait();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//                count = count + 1;
//                System.out.println(j);
            }
        }
    }

    private static class R2 implements Runnable {

        @Override
        public void run() {
            synchronized (o) {
                System.out.println("----");
                for (int i = 1; i <= 10; i++) {
                    int hh = i * 2 - 1;
                    while (!single) {
                        try {
                            o.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println(hh);
//                count = count + 1;
                    single = false;
                    o.notifyAll();
                }
//                while (count == 1) {
//                    try {
//                        System.out.println("-=-=-=-=-" + j);
//                        o.wait();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//                count = count + 1;
//                System.out.println(j);
            }
        }
    }

    private static class R3 implements Callable {

        @Override
        public Object call() throws Exception {
            synchronized (o) {
                System.out.println("----");
                for (int i = 1; i <= 10; i++) {
                    int hh = i * 2 - 1;
                    while (!single) {
                        try {
                            o.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println(hh);
//                count = count + 1;
                    single = false;
                    o.notifyAll();
                }
//                while (count == 1) {
//                    try {
//                        System.out.println("-=-=-=-=-" + j);
//                        o.wait();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//                count = count + 1;
//                System.out.println(j);
            }
            return 1;
        }
    }
}
