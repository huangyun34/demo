package com.my.admin.searchEngine.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * kafka安装配置步骤
 * 解压kafka安装包kafka_2.12-0.10.2.1.tgz
 * 配置
 * 编辑config/server.properties
 * 服务器127.0.0.1：
 * broker.id=1
 * delete.topic.enable=true
 * listeners=PLAINTEXT://127.0.0.1:9092
 * advertised.listeners=PLAINTEXT://127.0.0.1:9092
 * log.dirs=/storage/search-data/data/kafka
 * zookeeper.connect=127.0.0.1:2181
 *
 * ...其他保持不变
 * 集群改为单点，下面的服务区信息作废
 * 服务器10.101.80.6：
 * broker.id=2 delete.topic.enable=true listeners=PLAINTEXT://10.101.80.6:9092 advertised.listeners=PLAINTEXT://10.101.80.6:9092 log.dirs=/storage/search-data/data/kafka zookeeper.connect=10.101.52.228:2181,10.101.50.206:2181,10.101.52.229:2181 ...其他保持不变
 * 服务器10.101.80.7：
 * broker.id=2 delete.topic.enable=true listeners=PLAINTEXT://10.101.80.7:9092 advertised.listeners=PLAINTEXT://10.101.80.7:9092 log.dirs=/storage/search-data/data/kafka zookeeper.connect=10.101.52.228:2181,10.101.50.206:2181,10.101.52.229:2181 ...其他保持不变
 *
 *
 * 启动各个节点
 *
 * ./kafka-server-start.sh -daemon ../config/server.properties
 * 创建一个名为abc的topic
 *
 * ./kafka-topics.sh --create --zookeeper 127.0.0.1:2181 --replication-factor 1 --partitions 1 --topic abc
 *
 * 5.关闭
 *
 * bin/kafka-server-stop.sh
 *
 * 6.注意包与springboot的兼容问题
 */
@Configuration
@EnableKafka
public class KafkaConfig {

    /**
     * 生产者配置信息
     * @return
     */
    public Map<String, Object> producerMap() {
        Map<String, Object> map = new HashMap<>();
        map.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        map.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        map.put(ProducerConfig.BATCH_SIZE_CONFIG, "16348");
        map.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        map.put(ProducerConfig.RETRIES_CONFIG, "0");
        map.put(ProducerConfig.BUFFER_MEMORY_CONFIG, "33554432");
        map.put(ProducerConfig.LINGER_MS_CONFIG, "1");
        return map;
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        return new KafkaTemplate<>(new DefaultKafkaProducerFactory<>(producerMap()));
    }

    /**
     * 消费者配置信息
     * @return
     */
    public Map<String, Object> consumerMap() {
        Map<String, Object> map = new HashMap<>();
//        map.put(ConsumerConfig.GROUP_ID_CONFIG, "group");
        map.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
        map.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        map.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "40000");
        map.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        map.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        map.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        map.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
        return map;
    }

    @Bean
    public KafkaListenerContainerFactory kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory containerFactory = new ConcurrentKafkaListenerContainerFactory();
        containerFactory.setConsumerFactory(new DefaultKafkaConsumerFactory(consumerMap()));
        containerFactory.setConcurrency(1);
        containerFactory.getContainerProperties().setPollTimeout(3000);
        return containerFactory;
    }
}
