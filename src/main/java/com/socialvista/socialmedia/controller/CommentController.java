package com.socialvista.socialmedia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.socialvista.socialmedia.models.Comment;
import com.socialvista.socialmedia.models.User;
import com.socialvista.socialmedia.service.CommentService;
import com.socialvista.socialmedia.service.UserService;

@RestController
public class CommentController {
    
    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @PostMapping("/api/comments/createComment/post/{postId}")

    public Comment createComment(@RequestBody Comment comment,@RequestHeader ("Authorization") String jwt, @PathVariable ("postId") Integer postId) throws Exception{
        User user = userService.findUserByJwt(jwt);

        Comment createdComment = commentService.createComment(comment, postId, user.getId());
         
        return createdComment;

    }

    @PutMapping("/api/comments/likeComment/{commentId}")

    public Comment likeComment(@RequestHeader ("Authorization") String jwt, @PathVariable ("commentId") Integer commentId){
        
        User user = userService.findUserByJwt(jwt);

        Comment comment = commentService.likComment( commentId,user.getId());

        return comment;
    }

}
