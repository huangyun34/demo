package com.my.admin.searchEngine.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    @KafkaListener(id = "one", topics = "abc", containerGroup = "group")
    public void a(ConsumerRecord record) {
        String value = (String) record.value();
        System.out.println("结果:" + value);
    }
}
