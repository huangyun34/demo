package com.my.admin.config.rabbit;

import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.HashMap;

//@Component
public class RabbitListenerContainerFactoryConfig {

    @Autowired
    private ConnectionFactory connectionFactory;

    @Bean
    SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory1(){
        return getSimpleRabbitListenerContainerFactory(5);
    }

    @Bean
    SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory2(){
        return getSimpleRabbitListenerContainerFactory(6);
    }

    @Bean
    SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory3(){
        return getSimpleRabbitListenerContainerFactory(6);
    }

    private SimpleRabbitListenerContainerFactory getSimpleRabbitListenerContainerFactory(int consumers) {
        return getSimpleRabbitListenerContainerFactory(consumers, consumers);
    }

    private SimpleRabbitListenerContainerFactory getSimpleRabbitListenerContainerFactory(int concurrentConsumers,
                                                                                                int maxConcurrentConsumers) {
        SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory = new SimpleRabbitListenerContainerFactory();
        simpleRabbitListenerContainerFactory.setConnectionFactory(connectionFactory);
        simpleRabbitListenerContainerFactory.setConcurrentConsumers(concurrentConsumers);
        simpleRabbitListenerContainerFactory.setMaxConcurrentConsumers(maxConcurrentConsumers);
        return simpleRabbitListenerContainerFactory;
    }

}
