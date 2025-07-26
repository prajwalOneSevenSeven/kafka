package com.example.kafka_demo;

import com.example.kafka_demo.model.UserEvent;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<UserEvent, String> {
    // Optional: custom query methods
}