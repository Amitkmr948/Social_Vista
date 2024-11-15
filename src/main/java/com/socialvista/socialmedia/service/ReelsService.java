package com.socialvista.socialmedia.service;

import java.util.List;

import com.socialvista.socialmedia.models.Reels;
import com.socialvista.socialmedia.models.User;

public interface ReelsService {
    public Reels createReel(Reels reel,User user);

    public List<Reels>findAllReels();

    public List<Reels>findUsersReel(Integer userId);
    
}
