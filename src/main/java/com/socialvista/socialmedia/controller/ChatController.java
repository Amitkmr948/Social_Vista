package com.socialvista.socialmedia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.socialvista.socialmedia.models.Chat;
import com.socialvista.socialmedia.models.User;
import com.socialvista.socialmedia.request.CreateChatRequest;
import com.socialvista.socialmedia.service.ChatService;
import com.socialvista.socialmedia.service.UserService;

@RestController
public class ChatController {

    @Autowired
    private ChatService chatService;

    @Autowired
    private UserService userService;

    @PostMapping("/api/chat/createChat")
    public Chat createChat (@RequestHeader("Authorization") String jwt,@RequestBody CreateChatRequest chatRequest){
        User user1 = userService.findUserByJwt(jwt);
        User user2 = userService.findUserById(chatRequest.getUserId());
        
        Chat chat = chatService.createChat(user1, user2);
        return chat;
    }

    @GetMapping("/api/chat/getUserChats")
    public List<Chat>getUserChat(@RequestHeader("Authorization") String jwt){
        User user = userService.findUserByJwt(jwt);
       
        return chatService.findUsersChat(user.getId());
    }

    // @GetMapping("/api/chat/getChat")
    // public Chat findChatById(@RequestHeader("Authorization") String jwt){

    // }
    
}
