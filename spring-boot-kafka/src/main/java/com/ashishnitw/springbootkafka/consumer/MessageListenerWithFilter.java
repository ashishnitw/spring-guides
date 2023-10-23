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
public class MessageListenerWithFilter {

    /** In this listener, all the messages matching the filter will be discarded. **/

    @KafkaListener(topics = "ar-topic-filter",
            groupId = "consumer-group-filter")
    public void listen(@Payload ConsumerRecord<String, String> consumerRecord) {
        log.info("Received message: {}", consumerRecord.value());
    }

}
