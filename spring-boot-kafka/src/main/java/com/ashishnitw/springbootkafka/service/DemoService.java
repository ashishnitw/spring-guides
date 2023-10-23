package com.ashishnitw.springbootkafka.service;

import com.ashishnitw.springbootkafka.producer.MessageProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.ashishnitw.springbootkafka.constants.KafkaConstants.TOPIC;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class DemoService {

    private final MessageProducer kafkaProducer;

    public void publishMessage(String topic, String message) {
        topic = topic == null ? TOPIC : topic;
        kafkaProducer.sendMessage(topic, message);
        log.info("Message published successfully. Topic: {}, Message: {}", topic, message);
    }
}
