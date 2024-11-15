package com.socialvista.socialmedia.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.socialvista.socialmedia.models.Comment;

public interface CommentRepository extends JpaRepository<Comment,Integer> {
    
    

}
