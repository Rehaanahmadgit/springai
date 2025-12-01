package com.example.spring.ai.controler;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.google.genai.GoogleGenAiChatModel;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class multi_ai {
// if its not bean create during this process
    private ChatClient chatClient_ollama;

    private ChatClient chatClient_gimini;

    public multi_ai(GoogleGenAiChatModel googleGenAiChatModel , OllamaChatModel ollamaChatModel) {
        this.chatClient_ollama = ChatClient.builder(ollamaChatModel).build();
        this.chatClient_gimini = ChatClient.builder(googleGenAiChatModel).build();
    }


    @PostMapping("/message")
    public ResponseEntity<String> sendMessage(
            @RequestParam(required = true ,value = "q") String req
    ){
        String response = chatClient_gimini.prompt().user(req).call().content();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/chatmessage")
    public String chat(@RequestParam String ans) {

        return chatClient_gimini.prompt()
                .messages(
                        new SystemMessage("You are a helpful AI assistant."),
                        new UserMessage(ans)
                )

                .call()
                .content();
    }
}
