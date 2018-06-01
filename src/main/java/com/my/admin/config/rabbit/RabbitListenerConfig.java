package com.my.admin.config.rabbit;

import com.my.admin.model.Account;
import com.my.admin.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

//@Component
public class RabbitListenerConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitListenerConfig.class);

    @RabbitListener(
            id = "test",
            bindings = {
                    @QueueBinding(value = @Queue(name = "rabbit1", durable = "true", exclusive = "false", autoDelete = "false"),
                            exchange = @Exchange(name = "exchange1"), key = "key1")
            },
            containerFactory = "simpleRabbitListenerContainerFactory1"
    )
    public void listener1(String msg){
        LOGGER.info("listener1 Received <" + msg + ">");
    }

    @RabbitListener(
            bindings = {
                    @QueueBinding(value = @Queue(name = "rabbit2", durable = "true", exclusive = "false", autoDelete = "false"),
                            exchange = @Exchange(name = "exchange2"), key = "key2")
            },
            containerFactory = "simpleRabbitListenerContainerFactory2"
    )
    public void listener2(User user){
        LOGGER.info("listener2 Received");
        LOGGER.info(user.getUserName());
        LOGGER.info(user.getPassword());
    }

    @RabbitListener(
            bindings = {
                    @QueueBinding(value = @Queue(name = "rabbit3", durable = "true", exclusive = "false", autoDelete = "false"),
                            exchange = @Exchange(name = "exchange3"), key = "key3")
            },
            containerFactory = "simpleRabbitListenerContainerFactory3"
    )
    public void listener3(Account account){
        LOGGER.info("listener3 Received");
        LOGGER.info(account.getAddress());
    }
}
