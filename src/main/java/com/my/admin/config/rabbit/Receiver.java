package com.my.admin.config.rabbit;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

//@Component
public class Receiver {

//    private CountDownLatch latch = new CountDownLatch(1);
//
//    public void receiveMessage(String message) {
//        System.out.println("Received <" + message + ">");
//        latch.countDown();
//    }
//
//    @RabbitListener(queues = "nihao", containerFactory = "rabbitListenerContainerFactory")
//    public void listenerReceiveMessage(String message) {
//        System.out.println("listener Received <" + message + ">");
//    }
//
//    public CountDownLatch getLatch() {
//        return latch;
//    }
}
