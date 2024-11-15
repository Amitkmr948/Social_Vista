package com.socialvista.socialmedia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.socialvista.socialmedia.service.PostService;
import com.socialvista.socialmedia.service.UserService;
import com.socialvista.socialmedia.models.User;
import com.socialvista.socialmedia.models.UserPost;
import com.socialvista.socialmedia.response.ApiResponse;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController

@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    UserService userService;

    @Autowired
     PostService postService;

     @PostMapping("/createPost")
    public ResponseEntity<UserPost> createNewPost(@RequestBody UserPost post,@RequestHeader("Authorization") String Token){

        User userProfile = userService.findUserByJwt(Token);
        UserPost createdPost = postService.createNewPost(post,userProfile.getId());

        return new ResponseEntity<>(createdPost,HttpStatus.ACCEPTED);
    }


    @DeleteMapping("/deletePost/{postId}")
    public ResponseEntity<ApiResponse>deletePost(@PathVariable Integer postId, @RequestHeader("Authorization") String Token) throws Exception{

        User userProfile = userService.findUserByJwt(Token);
        String message = postService.deletePost(postId, userProfile.getId());

        ApiResponse res = new ApiResponse(message,true);

        return new ResponseEntity<ApiResponse>(res,HttpStatus.OK);
    }

    @GetMapping("/getPost/{postId}")
    public ResponseEntity<UserPost>findPostById(@PathVariable Integer postId){
        UserPost post = postService.findPostById(postId);

        return new ResponseEntity<UserPost>(post,HttpStatus.OK);
    }

    @GetMapping("/getPosts/{userId}")
    public ResponseEntity<List<UserPost>>findPostByUserId(@PathVariable Integer userId){
        List<UserPost> posts = postService.findPostByUserId(userId);

        return new ResponseEntity<List<UserPost>>(posts,HttpStatus.OK);
    }
    
     @GetMapping("/getAllPosts")
     public ResponseEntity<List<UserPost>>getAllPosts(){
        List<UserPost>posts = postService.findAllPost();
        return new ResponseEntity<List<UserPost>>(posts,HttpStatus.OK);
     }

     @PutMapping("/savedPost/{postId}")
     public ResponseEntity<UserPost>savePost(@PathVariable Integer postId,@RequestHeader("Authorization") String Token){
        
        User userProfile = userService.findUserByJwt(Token);
        UserPost savedPost = postService.savedPost(postId, userProfile.getId());

        return new ResponseEntity<UserPost>(savedPost,HttpStatus.ACCEPTED);
     }

      @PutMapping("/like/{postId}")
      public ResponseEntity<UserPost> likeAPost(@PathVariable Integer postId,@RequestHeader("Authorization") String Token){
        User userProfile = userService.findUserByJwt(Token);
        UserPost post = postService.likePost(postId, userProfile.getId());

        return new ResponseEntity<UserPost>(post,HttpStatus.ACCEPTED);
      }
     
}
