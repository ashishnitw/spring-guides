package com.ashishnitw.kafkajava.simpledemo;

import com.ashishnitw.kafkajava.Constants;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

public class ConsumerDemo {
    private static final String consumerGroup = "my-third-app";
    private static final String topic = "first_topic";

    public static void main(String[] args) {

        Properties props = new Properties();
        props.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, Constants.KAFKA_BROKERS);
        props.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.setProperty(ConsumerConfig.GROUP_ID_CONFIG, consumerGroup);
        props.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest"); // or latest or none

        // Create a Consumer
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);

        // Subscribe to topic
        consumer.subscribe(Arrays.asList(topic));   // can add more topics

        while (true) {
            ConsumerRecords<String, String> messages = consumer.poll(Duration.ofSeconds(1));
            for (ConsumerRecord<String, String> message : messages) {
                System.out.println("Message fetched >> " + message);
            }
        }
    }
}
