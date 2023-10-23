package com.ashishnitw.springbootkafka.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class MessageListenerWithRetry {

    @KafkaListener(topics = "ar-topic-retry", groupId = "consumer-group-retry", containerFactory = "containerFactoryWithRetry")
    public void listen(@Payload ConsumerRecord<String, String> consumerRecord) {
        log.info("Received message: {}", consumerRecord.value());
        throw new RuntimeException("Exception thrown intentionally");
    }

}
