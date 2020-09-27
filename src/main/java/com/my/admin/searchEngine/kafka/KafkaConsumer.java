package com.my.admin.searchEngine.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Component;

/**
 * 消费者数量需要与partition数量一致，如果不一致会导致
 * 1：消费者数量消费partition（不重复）总量>partition数量，多余消费者线程浪费
 * 2：消费者数量<partition（不重复）数量，有部分partition没有消费者，里面的数据不会消费
 * 但是一个消费者可以消费多个topic下的多个partition
 */
@Component
public class KafkaConsumer {

    /**
     * 单topic获取
     * @param record
     */
//    @KafkaListener(id = "one", topics = "abc", containerGroup = "group")
//    public void x(ConsumerRecord record) {
//        String value = (String) record.value();
//        System.out.println("结果:" + value);
//    }

    @KafkaListener(id = "one", containerGroup = "group", topicPartitions = {@TopicPartition(topic = "abc", partitions = {"0", "3", "4"}),
            @TopicPartition(topic = "def", partitions = {"0"})})
    public void a(ConsumerRecord record) {
        String value = (String) record.value();
        System.out.println("结果1:" + value);
    }

    @KafkaListener(id = "two", containerGroup = "group", topicPartitions = {@TopicPartition(topic = "abc", partitions = {"1"})})
    public void b(ConsumerRecord record) {
        String value = (String) record.value();
        System.out.println("结果2:" + value);
    }

    @KafkaListener(id = "three", containerGroup = "group1", topicPartitions = {@TopicPartition(topic = "abc", partitions = {"2"})})
    public void c(ConsumerRecord record) {
        String value = (String) record.value();
        System.out.println("结果3:" + value);
    }

//    @KafkaListener(id = "four", containerGroup = "group1", topicPartitions = {@TopicPartition(topic = "def", partitions = {"0"})})
//    public void d(ConsumerRecord record) {
//        String value = (String) record.value();
//        System.out.println("结果4:" + value);
//    }
}
