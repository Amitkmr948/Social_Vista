package com.socialvista.socialmedia.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exception.ResourceNotFoundException;
import com.socialvista.socialmedia.models.User;
import com.socialvista.socialmedia.models.UserPost;
import com.socialvista.socialmedia.repository.PostRepository;
import com.socialvista.socialmedia.repository.UserRepository;

@Service
public class PostServiceImplementation implements PostService {

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Override
    public UserPost createNewPost(UserPost post, Integer id) {
       
          User user = userService.findUserById(id);

          UserPost newPost = new UserPost();

          newPost.setCaption(post.getCaption());
          newPost.setImage(post.getImage());
          newPost.setVideo(post.getVideo());
          newPost.setCreatedAt(LocalDateTime.now());
          newPost.setUser(user);

          return postRepository.save(newPost);

    }

    @Override
    public String deletePost(Integer postId, Integer userId) throws Exception {
        UserPost post = findPostById(postId);
        User user = userService.findUserById(userId);

        if(post.getUser().getId() != user.getId()){
            throw new Exception("You are not eligible to delete this post");
        }

        postRepository.delete(post);
         return "post deleted";
    }

    @Override
    public List<UserPost> findPostByUserId(Integer userId) {
        // TODO Auto-generated method stub

        return postRepository.findPostByUserId(userId);
    }

    @Override
    public UserPost findPostById(Integer id) {
        
        UserPost post = postRepository.findById(id)
         .orElseThrow(()->
         new ResourceNotFoundException("Post is not found with the id : " + id)
         );

        return post;
    }

    @Override
    public List<UserPost> findAllPost() {
        
        List<UserPost> posts = postRepository.findAll();
        
        return posts;
    }

    @Override
    public UserPost savedPost(Integer postId, Integer userId) {
        // TODO Auto-generated method stub
        UserPost post = findPostById(postId);
        User user = userService.findUserById(userId);

        if(user.getSavedPost().contains(post)){
            user.getSavedPost().remove(post);
        }
        else{
            user.getSavedPost().add(post);
        }

        userRepository.save(user);

        return post;
       
    }

    @Override
    public UserPost likePost(Integer postId, Integer userId) {
       
        UserPost post = findPostById(postId);
        User user = userService.findUserById(userId);

        if(post.getLikedByUsers().contains(user)){
            post.getLikedByUsers().remove(user);
        }

        else{
            post.getLikedByUsers().add(user);
        }

       
        return postRepository.save(post);
    }
    
}
