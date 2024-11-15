package com.socialvista.socialmedia.service;

import com.socialvista.socialmedia.models.Comment;

public interface CommentService {
    public Comment createComment(Comment comment, Integer postId,Integer userId);

    public Comment likComment(Integer commentId,Integer userId);

    public Comment findCommentById(Integer commentId) ;
}
