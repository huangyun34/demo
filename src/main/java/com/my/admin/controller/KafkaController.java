package com.my.admin.controller;

import com.github.AopLog;
import com.my.admin.searchEngine.kafka.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/kafka/test")
@AopLog(type = "kafka", stackTraceOnErr = true)
public class KafkaController {

    @Autowired
    private KafkaProducer kafkaProducer;

    /**
     * 发送消息给kafka
     * @param value
     * @return
     */
    @RequestMapping(value = "/send", method = RequestMethod.POST)
    public String sendKafka(@RequestParam("value") String value){
        kafkaProducer.send(value);
        return "ok";
    }
}
