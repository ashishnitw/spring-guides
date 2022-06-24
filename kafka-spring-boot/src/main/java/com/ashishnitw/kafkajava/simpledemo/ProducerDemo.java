package com.ashishnitw.kafkajava.simpledemo;

import com.ashishnitw.kafkajava.Constants;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.Random;

public class ProducerDemo {
    private static final String topic = "first_topic";

    public static void main(String[] args) {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, Constants.KAFKA_BROKERS);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        KafkaProducer producer = new KafkaProducer(props);

        try {
            int start = new Random().nextInt(1000);
            for (int i = start; i < start + 10; i++) {
                ProducerRecord<String, String> message = new ProducerRecord<>(topic, String.valueOf(i), "This is order " + i);
                System.out.println("Sending message: " + message.toString());
                producer.send(message);
                Thread.sleep(1000);
            }
        } catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
        }

    }
}