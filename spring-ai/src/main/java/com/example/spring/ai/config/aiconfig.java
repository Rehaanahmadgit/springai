package com.example.spring.ai.config;

import org.checkerframework.checker.units.qual.C;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.google.genai.GoogleGenAiChatModel;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class aiconfig {
    // this is impotant before creation the bean
    //spring.ai.chat.client.enabled=false

    @Bean(name="googlegemini")
    public ChatClient gminichat(GoogleGenAiChatModel  googleGenAiChatModel) {
        return ChatClient.builder(googleGenAiChatModel).build();

    }

    @Bean(name = "ollamachat")
    public ChatClient ollamachat(OllamaChatModel ollamaChatModel) {
        return ChatClient.builder(ollamaChatModel).build();
    }
}
