package com.example.chatbot.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ConversationMessage {
    private String role;   // user / assistant
    private String content;

    public ConversationMessage(String role, String content) {
        this.role = role;
        this.content = content;
    }

    // getters and setters
}