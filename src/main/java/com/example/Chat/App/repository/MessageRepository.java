package com.example.Chat.App.repository;

import com.example.Chat.App.model.Message;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MessageRepository extends MongoRepository<Message, String> {
}

