package com.socialvista.socialmedia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.socialvista.socialmedia.models.Reels;

public interface ReelsRepository extends JpaRepository<Reels,Integer> {
    
    public List<Reels>findByUserId(Integer userId);

}
