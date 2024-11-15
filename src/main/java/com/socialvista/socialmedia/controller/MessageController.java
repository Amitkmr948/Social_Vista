package com.socialvista.socialmedia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.socialvista.socialmedia.models.Message;
import com.socialvista.socialmedia.models.User;
import com.socialvista.socialmedia.service.MessageService;
import com.socialvista.socialmedia.service.UserService;

@RestController
public class MessageController {
    @Autowired
    private MessageService messageService;

    @Autowired 
    private UserService userService;

    @PostMapping("api/message/createMessage/{chatId}")
    public Message creatMessage(@RequestHeader("Authorization") String jwt,@RequestBody Message req,@PathVariable Integer chatId){
        
        User user = userService.findUserByJwt(jwt);
        Message message = messageService.createMessage(user, chatId, req);
        
        return message;
    }

    @GetMapping("/api/message/findChatsMessage/{chatId}")
    public List<Message>findChatsMessages(@RequestHeader("Authorization") String jwt,@PathVariable Integer chatId){
        User user = userService.findUserByJwt(jwt);
        List<Message>messages = messageService.findChatsMessages(chatId);
        return messages;
    }
}
