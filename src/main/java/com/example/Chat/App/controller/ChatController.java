
package com.example.Chat.App.controller;







import com.example.Chat.App.service.MessageProducer;
import com.example.Chat.App.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    @Autowired
    private MessageProducer messageProducer;

    @MessageMapping("/send")
    @SendTo("/topic/messages")
    public Message sendMessage(Message message) {
       MessageProducer.sendMessage(message.getContent());
        return message;
    }
}
