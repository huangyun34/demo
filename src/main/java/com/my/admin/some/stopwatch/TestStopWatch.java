package com.my.admin.some.stopwatch;

import org.springframework.util.StopWatch;

public class TestStopWatch {
    public static void main(String[] args) throws InterruptedException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("吃饭");
        Thread.sleep(100);
        stopWatch.stop();
        stopWatch.start("睡觉");
        Thread.sleep(100);
        stopWatch.stop();
        stopWatch.start("洗衣服");
        Thread.sleep(100);
        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());
    }
}
