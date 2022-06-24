package com.ashishnitw.kafkajava;

import com.ashishnitw.kafkajava.Constants;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Arrays;
import java.util.Properties;

public class ConsumerDemoAssignSeek {

    public static void main(String[] args) {

        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, Constants.KAFKA_BROKERS);  // kafka Address
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest"); // or latest or none

        String topic = "first_topic";
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);

        // assign and seek are mostly used to replay data or fetch a specific message

        // assign
        TopicPartition partitionToReadFrom = new TopicPartition(topic, 0);
        consumer.assign(Arrays.asList(partitionToReadFrom));

        // seek
        long offsetToReadFrom = 5L;
        consumer.seek(partitionToReadFrom, offsetToReadFrom);

        int numOfMessagesToRead = 4;
        int numOfMessagesReadSoFar = 0;
        boolean keepOnReading = true;

        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(100); // new in kafka
            for (ConsumerRecord record : records) {
                numOfMessagesReadSoFar++;
                System.out.println("Key: " + record.key() + ", Value: " + record.value() + ", Partition: " + record.partition() + ", Offset: " + record.offset());
            }
            if (numOfMessagesReadSoFar >= numOfMessagesToRead) {
                keepOnReading = false;
                break;
            }
        }
        System.out.println("Exiting the application");
    }
}
