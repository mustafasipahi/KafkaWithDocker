package com.kafka.service;

import com.kafka.model.KafkaMessage;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProducerService {

    private final KafkaTemplate<String, KafkaMessage> kafkaTemplate;

    public ProducerService(KafkaTemplate<String, KafkaMessage> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String topic, String key, KafkaMessage message) {
        kafkaTemplate.send(topic, key, message);
    }
}