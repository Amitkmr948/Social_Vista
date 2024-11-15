package com.socialvista.socialmedia.service;

import java.util.List;

import com.socialvista.socialmedia.models.UserPost;

public interface PostService {
   
    UserPost createNewPost(UserPost post,Integer id);

    String deletePost(Integer postId,Integer userId) throws Exception;

    List<UserPost>findPostByUserId(Integer userId);

    UserPost findPostById(Integer id);

    List<UserPost>findAllPost();

    UserPost savedPost(Integer postId, Integer userId);

    UserPost likePost(Integer postId,Integer userId);

    
} 
