package com.ashishnitw.springbootkafka.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class MessageListenerWithHeader {

    @KafkaListener(topics = "ar-topic-header", groupId = "consumer-group-header")
    public void listen(@Payload ConsumerRecord<String, String> consumerRecord,
                       @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partitionId,
                       @Header(KafkaHeaders.OFFSET) int offset) {
        log.info("Received message: {}. Partition: {}. Offset: {}", consumerRecord.value(), partitionId, offset);
    }

}
