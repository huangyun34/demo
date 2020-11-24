package com.my.admin.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class StringDecode {


    public void solution(String s) {

    }

    public static void main(String[] args) throws InterruptedException {
        ReentrantLock reentrantLock = new ReentrantLock();
        Condition condition = reentrantLock.newCondition();
        new Thread(() -> {
            reentrantLock.lock();
            try {
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(1232);
//            condition.signal();
            reentrantLock.unlock();
        }).start();

        new Thread(() -> {
            reentrantLock.lock();
            try {
                condition.await(5, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            System.out.println(123);
            condition.signalAll();
            reentrantLock.unlock();
        }).start();
//        ReentrantLock reentrantLock = new ReentrantLock();
//        System.out.println((-1 << 29) | 0);
//        System.out.println(((-1 << 29) | 0) & ~(1 << 29) - 1);
//        System.out.println(((-1 << 29) | 0) & (1 << 29) - 1);
//        System.out.println(-1 << 29);
//        System.out.println(0 << 29);
//        System.out.println(1 << 29);
//        System.out.println(2 << 29);
//        System.out.println(3 << 29);
//        String s = "3[a2[c]]";
////        String s = "2[abc]3[cd]ef";
////        String s = "abc3[cd]xyz";
////        String s = "3[a]2[bc]";
//        for (int i = 0; i < s.length(); i++) {
//
//        }

    }
}
