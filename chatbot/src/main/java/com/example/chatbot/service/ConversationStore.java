package com.example.chatbot.service;


import com.example.chatbot.entity.Conversation;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;
@Service
public class ConversationStore {

    private static final ConcurrentHashMap<String, Conversation> store = new ConcurrentHashMap<>();

    public static Conversation get(String userId) {

//        System.out.println(store.get(userId));
//
//        System.out.println("............................................");
//        System.out.println(store);
        return store.computeIfAbsent(userId, Conversation::new);
    }

    public static void save(Conversation conversation) {
        store.put(conversation.getUserId(), conversation);
    }
}