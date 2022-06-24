package com.ashishnitw.kafkajava.withkeys;

import com.ashishnitw.kafkajava.Constants;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class ProducerWithKeys {
    public static void main(String[] args) {

        // Create producer properties
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, Constants.KAFKA_BROKERS);  // kafka Address
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        // Create producer
        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(properties);

        for (int i=0; i<10; i++) {
            // Create a producer record
            String topic = "first_topic";
            String value = "hello world " + String.valueOf(i);
            String key = "id_" + String.valueOf(i);
            ProducerRecord<String, String> record = new ProducerRecord<String, String>(topic, key, value);

            System.out.println("key: " + key);

            // Send data - asynchronous
            producer.send(record, new Callback() {
                public void onCompletion(RecordMetadata metadata, Exception e) {
                    // executes every time a record is successfully sent or an exception is thrown
                    if (e == null) {
                        // record was successfully sent
                        System.out.println("Received new metadata:\n" +
                                "Topic: " + metadata.topic() + "\n" +
                                "Partition: " + metadata.partition() + "\n" +
                                "Offset: " + metadata.offset() + "\n" +
                                "Timestamp: " + metadata.timestamp() + "\n");
                    } else {
                        System.out.println("Error while producing" + e.getMessage());
                    }
                }
            });
        }

        // flush
        producer.flush();
        // close
        producer.close();

    }
}
