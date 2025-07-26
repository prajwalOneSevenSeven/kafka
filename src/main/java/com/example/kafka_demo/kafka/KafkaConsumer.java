package com.example.kafka_demo.kafka;

import com.example.kafka_demo.model.UserEvent;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@EnableKafka
public class KafkaConsumer {

    private final SimpMessagingTemplate messagingTemplate;

    public KafkaConsumer(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    // This method will be triggered every time a message is received
    @KafkaListener(topics = "${app.kafka.topic-order-created}", groupId = "my-group")
    public void consume(UserEvent userEvent) {
        System.out.println("✅ Message consumed: " + userEvent);

        // Send message to WebSocket clients
        messagingTemplate.convertAndSend("/topic/messages", userEvent);
    }
}