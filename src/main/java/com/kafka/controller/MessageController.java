package com.kafka.controller;

import com.kafka.model.KafkaMessage;
import com.kafka.service.ProducerService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/message")
public class MessageController {

    @Value("${kafka.topic.testTopic}")
    private String topic;

    @Value("${kafka.key.testKey}")
    private String key;

    private final ProducerService producerService;

    public MessageController(ProducerService producerService) {
        this.producerService = producerService;
    }

    @PostMapping("/send")
    public void sendMessage(@RequestBody String message) {
        final KafkaMessage kafkaMessage = new KafkaMessage();
        kafkaMessage.setMessage(message);
        producerService.sendMessage(topic, key, kafkaMessage);
    }
}
