package com.ashishnitw.springbootkafka.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class MessageProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String topicName, String message) {
        kafkaTemplate.send(topicName, message);
    }

    public void sendMessageWithKey(String topicName, String key, String message) {
        kafkaTemplate.send(topicName, key, message);
    }
}
