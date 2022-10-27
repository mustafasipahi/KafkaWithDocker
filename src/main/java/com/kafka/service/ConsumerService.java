package com.kafka.service;

import com.kafka.model.KafkaMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {

    private final Logger logger = LoggerFactory.getLogger(ConsumerService.class);

    @KafkaListener(topics = "${kafka.topic.testTopic}")
    public void consume(KafkaMessage kafkaMessage) {
        logger.info(kafkaMessage.getMessage());
    }

    @KafkaListener(topics = "${kafka.topic.testTopic}")
    public void consume(final @Payload KafkaMessage kafkaMessage,
                        final @Header(KafkaHeaders.OFFSET) Integer offset,
                        final @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String key,
                        final @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
                        final @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                        final @Header(KafkaHeaders.RECEIVED_TIMESTAMP) long ts) {

        logger.info(String.format("Message:%s Offset:%s Key:%s Partition:%s Topic:%s Ts:%s", kafkaMessage.getMessage(), offset, key, partition, topic, ts));
    }
}
