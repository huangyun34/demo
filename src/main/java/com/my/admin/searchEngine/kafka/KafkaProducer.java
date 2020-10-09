package com.my.admin.searchEngine.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

//@Component
public class KafkaProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducer.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    /**
     * 向kafka发送消息
     * @param message
     */
    public void send(String message) {
        LOGGER.info("往topic:abc发送消息{}", message);
        kafkaTemplate.send("abc", message);
        kafkaTemplate.flush();
    }

    /**
     * 向kafka发送消息
     * @param message
     */
    public void send1(String message) {
        LOGGER.info("往topic:def发送消息{}", message);
        kafkaTemplate.send("def", message);
        kafkaTemplate.flush();
    }
}
