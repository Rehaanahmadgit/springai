package com.example.chatbot.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Data
@Getter
@Setter
public class Conversation {

    private String userId;
    private List<ConversationMessage> messages = new ArrayList<>();

    public Conversation(String userId) {
        this.userId = userId;
    }

    public void addMessage(ConversationMessage message) {
        this.messages.add(message);
    }

    // getters and setters
}
