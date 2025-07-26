package com.example.kafka_demo.order;

import com.example.kafka_demo.kafka.GenericKafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class OrderService {

    @Autowired
    OrderRepository repository;

    @Value("${app.kafka.topic-order-created}")
    private String orderCreatedTopic;

    @Autowired
    private GenericKafkaProducer<Object> kafkaProducer;


    public Order placeOrder(Order order) {
        Order savedOrder = repository.save(order);
        if(Objects.nonNull(savedOrder)){
            OrderController.OrderEvent orderEvent = new OrderController.OrderEvent(savedOrder.getId(), savedOrder.getCustomerName(), savedOrder.getProduct(), savedOrder.getQuantity(), savedOrder.getTotalAmount(), savedOrder.getOrderDate());
            kafkaProducer.send(orderCreatedTopic, orderEvent.getId(), orderEvent);
        }
        return savedOrder;
    }
}