package com.my.admin.config.rabbit;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.DirectMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

//@Configuration
@Deprecated
public class RabbitMQBinding {

//    @Autowired
//    private ConnectionFactory connectionFactory;
//
//    @Bean
//    Exchange exchange(){
//        return new DirectExchange("chifan");
//    }
//
//    @Bean
//    Queue queue(){
//        return new Queue("nihao", false);
//    }
//
//    @Bean
//    Binding binding(Queue queue, Exchange exchange){
//        return BindingBuilder.bind(queue).to(exchange).with("key").noargs();
//    }
//
//    @Bean
//    SimpleMessageListenerContainer container(
//                                             MessageListenerAdapter listenerAdapter) {
//        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
//        container.setConnectionFactory(connectionFactory);
//        container.setQueueNames("nihao");
//        container.setMessageListener(listenerAdapter);
//        container.setConcurrentConsumers(5);
//        container.setMaxConcurrentConsumers(10);
//        return container;
//    }
//
//    @Bean
//    SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(/*ConnectionFactory connectionFactory*/){
//        SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory = new SimpleRabbitListenerContainerFactory();
//        simpleRabbitListenerContainerFactory.setConnectionFactory(connectionFactory);
//        simpleRabbitListenerContainerFactory.setConcurrentConsumers(10);
//        simpleRabbitListenerContainerFactory.setMaxConcurrentConsumers(20);
//        return simpleRabbitListenerContainerFactory;
//    }
//
//
//    @Bean
//    MessageListenerAdapter listenerAdapter(Receiver receiver) {
//        return new MessageListenerAdapter(receiver, "receiveMessage");
//    }
}
