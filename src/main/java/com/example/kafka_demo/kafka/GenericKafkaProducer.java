package com.example.kafka_demo.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class GenericKafkaProducer<T> {

    private final KafkaTemplate<String, T> kafkaTemplate;

    public GenericKafkaProducer(KafkaTemplate<String, T> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(String topic, String key, T message) {
        kafkaTemplate.send(topic, key, message);
        System.out.println("✅ Sent message: " + message);
    }

    public void send(String topic, T message) {
        kafkaTemplate.send(topic, message);
        System.out.println("✅ Sent message: " + message);
    }
}