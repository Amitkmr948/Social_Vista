package com.socialvista.socialmedia.service;

import java.util.List;

import com.socialvista.socialmedia.models.Message;
import com.socialvista.socialmedia.models.User;

public interface MessageService {
    public Message createMessage(User user,Integer chatId,  Message req);
    public List<Message>findChatsMessages(Integer chatId);
}
