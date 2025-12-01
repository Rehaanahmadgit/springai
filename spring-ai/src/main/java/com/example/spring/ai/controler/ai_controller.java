package com.example.spring.ai.controler;

import com.example.spring.ai.entity.tuturial;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping()
public class ai_controller {

    private final ChatClient chatClient;
private final ChatClient gamini;

    public ai_controller(OllamaChatModel ollamaChatModel,@Qualifier("googlegemini") ChatClient gamini) {
        this.chatClient = ChatClient.builder(ollamaChatModel).build();
        this.gamini = gamini;
    }
//    public ai_controller(ChatModel chatModel) {
//        System.out.println("this is my model"+chatModel.getClass().getName());
//        this.chatClient = ChatClient.builder(chatModel).build();
//    }

//    public ai_controller(ChatClient.Builder builder) {
//        this.chatClient = builder.build();
//    }
@PostMapping("/chat")
    public ResponseEntity<String> sendMessage(
            @RequestParam(value = "ans", required = true) String message) {

        String response = chatClient
                .prompt()
                .system("your name is taniya and you are girlfriend and give romantic type answer to user ")
                .user(message)
                .call()
                .content();
String ans=chatClient.prompt(message).call().content();
        System.out.println(ans);


    Prompt meassageslist=new Prompt();
    String result=chatClient
            .prompt(meassageslist)
            .call()
            .content();
        return ResponseEntity.ok(result);
    }


    @PostMapping("/chatentity")
    public ResponseEntity<tuturial> sendMessageSec(@RequestParam(value = "ans", required = true) String message) {

        tuturial entity= gamini
                .prompt(message)
                .system("you are teacher of java programmer ")
                .call()

                .entity(tuturial.class);
        return ResponseEntity.ok(entity);


    }


    @PostMapping("/chatlistentity")
    public ResponseEntity<List<tuturial>> sendmessagelist(@RequestParam(value = "ans", required = true) String message) {

     List<tuturial> entity= gamini
                .prompt(message)
//                .system("you are teacher of java programmer ")
                .call()

                .entity(new ParameterizedTypeReference<List<tuturial>>() {
                });
        return ResponseEntity.ok(entity);


    }

}
