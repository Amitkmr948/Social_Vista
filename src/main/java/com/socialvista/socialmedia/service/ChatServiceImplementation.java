package com.socialvista.socialmedia.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exception.ResourceNotFoundException;
import com.socialvista.socialmedia.models.Chat;
import com.socialvista.socialmedia.models.User;
import com.socialvista.socialmedia.repository.ChatRepository;

@Service
public class ChatServiceImplementation implements ChatService {

    @Autowired ChatRepository chatRepository;

    @Override
    public Chat createChat(User userId1, User userId2) {
           Chat isExist = chatRepository.findChatByUsersId(userId2, userId1);
           if(isExist != null){
            return isExist;
           }

           Chat chat = new Chat();
           chat.getUsers().add(userId2);
           chat.getUsers().add(userId1);
           chat.setTimestamp(LocalDateTime.now());
        
           return chatRepository.save(chat);
    }

    @Override
    public Chat findChatById(Integer chatId) {
        
        Chat chat = chatRepository.findById(chatId).orElseThrow(()->
         new ResourceNotFoundException("Chat is not found with the id : " + chatId)
         );

         return chat;

    }

    @Override
    public List<Chat> findUsersChat(Integer userId) {
       
        return chatRepository.findByUsersId(userId);
    
    }

    
}
