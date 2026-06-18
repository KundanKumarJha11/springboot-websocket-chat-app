package com.jhas.Chat_App.controller;

import com.jhas.Chat_App.model.ChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/chat")
    public void sendMessage(ChatMessage message) {

        System.out.println(
                "Received: " +
                        message.getSender() +
                        " -> " +
                        message.getReceiver() +
                        " : " +
                        message.getContent()
        );

        messagingTemplate.convertAndSend(
                "/topic/messages",
                message
        );
    }
}