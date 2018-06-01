package com.my.admin;

import com.my.admin.model.Account;
import com.my.admin.model.User;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

//@Component
public class Runner implements CommandLineRunner {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private Account account;

    @Value("${account.address}")
    private String address;

//    @Autowired
//    private Receiver receiver;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Sending message..." + address);
//        for (int i =0; i < 10; i++){
//            rabbitTemplate.convertAndSend("exchange1", "key1", "1 Hello from RabbitMQ!");
//        }
//        User user = new User("huangyun", "123456");
//        rabbitTemplate.convertAndSend("exchange2", "key2", user);
//        rabbitTemplate.convertAndSend("exchange3", "key3", "3 Hello from RabbitMQ!");
//        receiver.getLatch().await(1000, TimeUnit.MILLISECONDS);
        rabbitTemplate.convertAndSend("exchange3", "key3", account);
        System.out.println("不等了。。。");
    }
}
