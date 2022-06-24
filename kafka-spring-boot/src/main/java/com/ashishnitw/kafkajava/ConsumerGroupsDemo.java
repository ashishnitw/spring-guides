package com.ashishnitw.kafkajava;

import com.ashishnitw.kafkajava.Constants;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Arrays;
import java.util.Properties;

public class ConsumerGroupsDemo {
    public static void main(String[] args) {
        String groupId = "my-third-app";
        Properties properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, Constants.KAFKA_BROKERS);  // kafka Address
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest"); // or latest or none

        String topic = "first_topic";
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(properties);
        consumer.subscribe(Arrays.asList(topic));   // can add more topics

        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(100); // new in kafka
            for (ConsumerRecord record : records) {
                System.out.println("Key: " + record.key() + ", Value: " + record.value() + ", Partition: " + record.partition() + ", Offset: " + record.offset());
            }
        }
    }
}
