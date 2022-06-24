package com.ashishnitw.kafkajava;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;

public class ConsumerDemoThreads {
    public static void main(String[] args) {
        String groupId = "my-fourth-app";
        Properties properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, Constants.KAFKA_BROKERS);  // kafka Address
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest"); // or latest or none

        final CountDownLatch latch = new CountDownLatch(1);
        final Runnable myConsumerRunnable = new ConsumerThread(latch, properties);
        Thread myThread = new Thread(myConsumerRunnable);
        myThread.start();

        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            public void run() {
                System.out.println("Caught shutdown hook");
                ((ConsumerThread) myConsumerRunnable).shutdown();
                try {
                    latch.await();
                } catch (InterruptedException e) {
                        e.printStackTrace();
                }
                System.out.println("Application has exited");
            }
        }));

        try {
            latch.await();
        } catch (InterruptedException e) {
            System.out.println("System is closing");
        }
    }
}

class ConsumerThread implements Runnable {
    private CountDownLatch latch;
    private KafkaConsumer<String, String> consumer;
    public ConsumerThread(CountDownLatch latch, Properties properties) {
        this.latch = latch;
        consumer = new KafkaConsumer<String, String>(properties);
        String topic = "first_topic";
        consumer.subscribe(Arrays.asList(topic));   // can add more topics
    }
    public void run() {
        try {
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(100); // new in kafka
                for (ConsumerRecord record : records) {
                    System.out.println("Key: " + record.key() + ", Value: " + record.value() + ", Partition: " + record.partition() + ", Offset: " + record.offset());
                }
            }
        } catch (WakeupException e) {
            System.out.println("Received shutdown signal");
        } finally {
            consumer.close();
            latch.countDown();  // tells main code we are done with consumer
        }
    }
    public void shutdown() {
        consumer.wakeup();  // to interrupt consumer.pol()
    }
}
