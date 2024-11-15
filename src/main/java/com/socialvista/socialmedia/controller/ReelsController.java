package com.socialvista.socialmedia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.socialvista.socialmedia.models.Reels;
import com.socialvista.socialmedia.models.User;
import com.socialvista.socialmedia.service.ReelsService;
import com.socialvista.socialmedia.service.UserService;

@RestController
public class ReelsController {

    @Autowired
    private ReelsService reelsService;

    @Autowired
    private UserService userService;

    @PostMapping("/api/reels/createreel")
    public Reels createReels(@RequestBody Reels reel,@RequestHeader("Authorization") String jwt){

        User user = userService.findUserByJwt(jwt); 
            Reels createReel = reelsService.createReel(reel, user);       
        return createReel;
    }

    @GetMapping("/api/reels/getUserReels/{userId}")
    public List<Reels> getUserReels(@RequestHeader("Authorization") String jwt,@PathVariable int userId) throws Exception{
          
        List<Reels>reels = reelsService.findUsersReel(userId);

        return reels;
    }

    @GetMapping("/api/reels/getAllReels")
    public List<Reels>getAllReels(@RequestHeader("Authorization") String jwt){

        List<Reels>reels = reelsService.findAllReels();
        return reels;
    }
    
}
