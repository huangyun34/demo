package com.my.admin.javabasic.threadlocal;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadLocalTest {
    private static ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(() -> 1);
    private static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(20, 20, 0, TimeUnit.SECONDS, new ArrayBlockingQueue<>(100000000));
    private static int count = 0;
    private static AtomicInteger atomicInteger;
    public static int add(int i) {
        ReentrantLock reentrantLock = new ReentrantLock();
        Condition condition = reentrantLock.newCondition();
        threadLocal.set(i);
        int num = threadLocal.get() + 1;
//        threadLocal.remove();
        return num;
    }
    public static void main(String[] args) throws InterruptedException, NoSuchFieldException, IllegalAccessException {
        atomicInteger.compareAndSet(0, 1);
        Integer i = 4;
        Integer i1 = 4;
        i1.toString();
//        for (int i = 0; i < 20; i++) {
//            threadPoolExecutor.execute(() -> {
//                int sum = add(count);
//                System.out.println(sum);
//            });
//        }
//        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
//        concurrentHashMap.put(1, "123");
//        System.out.println(concurrentHashMap.isEmpty());
//        concurrentHashMap.forEach((k, v) -> {
//            System.out.println(k + " " + v);
//        });
//        Object ds = concurrentHashMap.remove(1);
//        System.out.println(ds);
//        System.out.println(16 >> 2);
//        System.out.println(16 >>> 2);
//        Field field = Unsafe.class.getDeclaredField("theUnsafe");
//        field.setAccessible(true);
//        Unsafe unsafe = (Unsafe)field.get(null);
////        Unsafe unsafe = Unsafe.getUnsafe();
//        long valueOffset = unsafe.objectFieldOffset
//                (AtomicInteger.class.getDeclaredField("value"));
//        System.out.println(unsafe.compareAndSwapInt(1, valueOffset, 1, 1));
    }
}
