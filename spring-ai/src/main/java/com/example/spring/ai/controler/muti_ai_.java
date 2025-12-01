package com.example.spring.ai.controler;


import org.springframework.ai.chat.client.ChatClient;

import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class muti_ai_ {

    ChatClient chatClient_gimini;
    ChatClient chatClient_ollama;

    public muti_ai_(@Qualifier("googlegemini") ChatClient chatClient_gimini, @Qualifier("ollamachat") ChatClient chatClient_ollama) {
        this.chatClient_gimini = chatClient_gimini;
        this.chatClient_ollama = chatClient_ollama;
    }


    @PostMapping("/multichat_gimini")
    public ResponseEntity<String> multi_gmini(@RequestParam(value = "ans",required = true)
                                         String req)
    {
        String ans = chatClient_gimini.prompt(req).call().content();
        return ResponseEntity.ok(ans);
    }

    @PostMapping("/multichat_ollama")
    public ResponseEntity<String> multi_ollama(@RequestParam(value = "ans",required = true)
                                         String req)
    {
        String ans = chatClient_ollama.prompt(req).call().content();
        return ResponseEntity.ok(ans);
    }
}
