package com.ashishnitw.springbootkafka.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class MessageListenerFromSpecificPartition {

    @KafkaListener(
            topicPartitions = @TopicPartition(topic = "ar-specific-partition", partitions = {"0", "3"}),
            groupId = "consumer-group-specific-partition")
    public void listenToPartitions(@Payload ConsumerRecord<String, String> consumerRecord,
                                   @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {
        log.info("Received message: {}", consumerRecord.value());
    }

}
