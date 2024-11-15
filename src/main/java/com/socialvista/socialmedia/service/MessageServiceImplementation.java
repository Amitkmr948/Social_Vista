package com.socialvista.socialmedia.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.socialvista.socialmedia.models.Chat;
import com.socialvista.socialmedia.models.Message;
import com.socialvista.socialmedia.models.User;
import com.socialvista.socialmedia.repository.ChatRepository;
import com.socialvista.socialmedia.repository.MessageRepository;




@Service
public class MessageServiceImplementation implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private ChatService chatService;

    @Autowired ChatRepository chatRepository;

    @Override
    public Message createMessage(User user, Integer chatId, Message req) {
       
        Chat chat = chatService.findChatById(chatId);
       
        Message message = new Message();
         
        message.setChat(chat);
        message.setContent(req.getContent());
        message.setImage(req.getImage());
        message.setUser(user);
        message.setTimestamp(LocalDateTime.now());

        Message savedMessage =  messageRepository.save(message);

        chat.getMessages().add(savedMessage);
        chatRepository.save(chat);
        return savedMessage;
    }

    @Override
    public List<Message> findChatsMessages(Integer chatId) {
    
        Chat chat = chatService.findChatById(chatId);

        return messageRepository.findByChatId(chatId);
    }
    
}
