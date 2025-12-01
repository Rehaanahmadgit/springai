package com.example.chatbot.controler;


import com.example.chatbot.entity.Conversation;
import com.example.chatbot.entity.ConversationMessage;
import com.example.chatbot.service.ConversationStore;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    private final ChatClient chatClient;

    public ChatController(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    @PostMapping
    public String chat(@RequestParam String userId, @RequestBody String userText) {

        // 1. Load old conversation of this user
        Conversation conversation = ConversationStore.get(userId);

        // 2. Add new user message
        conversation.addMessage(new ConversationMessage("user", userText));

        // 3. Build message list for AI
        var prompt = chatClient.prompt().messages(
                new SystemMessage("You are a helpful AI assistant. Use previous conversation for context.")
        );

        // add historical messages
        conversation.getMessages().forEach(msg -> {
            if (msg.getRole().equals("user")) {
                prompt.messages(new UserMessage(msg.getContent()));
            } else {
                prompt.messages(new AssistantMessage(msg.getContent()));
            }
        });

        // 4. Call AI
        String aiResponse = prompt.call().content();

        // 5. Save AI response into history
        conversation.addMessage(new ConversationMessage("assistant", aiResponse));
        ConversationStore.save(conversation);

        // 6. Return reply
        return aiResponse;
    }
}
