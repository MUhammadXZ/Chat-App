package com.example.Chat.App.service;

import com.example.Chat.App.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageProducer {

    @Autowired
    private static KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private KafkaTemplate<String, Message> kafkaTemplateMongo;

    public static void sendMessage(String message) {
        kafkaTemplate.send("chat-messages", message);
    }

    public void sendMessageToMongo(Message message) {
        kafkaTemplateMongo.send("chat-messages-mongo", message);
    }
}
