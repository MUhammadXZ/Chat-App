package com.example.Chat.App.service;



import com.example.Chat.App.model.Message;
import com.example.Chat.App.repository.MessageRepository;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class KafkaConsumerService {
    @Autowired
    private MessageRepository messageRepository;

    @KafkaListener(topics = "${spring.kafka.topic.chat-messages}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(ConsumerRecord<String, String> record) {
        Message message = new Message();
        message.setContent(record.value());
        message.setTimestamp(LocalDateTime.now());
        messageRepository.save(message);
    }
}

