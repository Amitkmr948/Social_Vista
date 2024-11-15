package com.socialvista.socialmedia.service;

import java.util.List;

import com.socialvista.socialmedia.models.Chat;
import com.socialvista.socialmedia.models.User;

public interface ChatService {
    
    public Chat createChat(User userId1, User userId2);

    public Chat findChatById(Integer chatId);

    public List<Chat>findUsersChat(Integer userId);

}
