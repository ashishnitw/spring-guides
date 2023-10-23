package com.ashishnitw.springbootkafka.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import static com.ashishnitw.springbootkafka.constants.KafkaConstants.CONSUMER_GROUP_ID_1;
import static com.ashishnitw.springbootkafka.constants.KafkaConstants.TOPIC;

@Slf4j
@Component
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class MessageListenerMultipleTopics {

    //TODO: Need to re-check this
    @KafkaListener(topics = "ar-topic-multiple-1", groupId = "consumer-group-multiple-topics")
    public void listen(@Payload ConsumerRecord<String, String> consumerRecord) {
        log.info("Received message: {}", consumerRecord.value());
    }

}
