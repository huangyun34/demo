package com.my.admin.javabasic.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {

    //实例方法以实例作为锁，需注意
    public synchronized void t1() throws InterruptedException {
        System.out.println("------");
        Thread.sleep(5000);
        System.out.println("======");
    }

    //类方法以类作为锁
    public synchronized static void t2() throws InterruptedException {
        System.out.println("------");
        Thread.sleep(5000);
        System.out.println("======");
    }

    public void t3() throws InterruptedException {
        //this 指代当前对象,无法作用与类方法。
        synchronized (this){
            System.out.println("------");
            Thread.sleep(5000);
            System.out.println("======");
        }
    }

//    public static void t4() throws InterruptedException {
//        synchronized (this){
//            System.out.println("------");
//            Thread.sleep(5000);
//            System.out.println("======");
//        }
//    }

    public void t5() throws InterruptedException {
        //this 指代当前对象,无法作用与类方法。
        synchronized (this){
            this.notifyAll();
            this.wait();
            System.out.println("------");
            Thread.sleep(5000);
            System.out.println("======");
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
//        for (int i = 0; i < 5; i++) {
//            executorService.submit(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        Test.t2();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            });
//        }

        Test ds = new Test();
//        for (int i = 0; i < 5; i++) {
//            executorService.submit(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        ds.t1();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            });
//        }

        for (int i = 0; i < 5; i++) {
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        ds.t5();

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
