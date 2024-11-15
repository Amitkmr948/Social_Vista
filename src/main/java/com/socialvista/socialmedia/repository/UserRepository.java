package com.socialvista.socialmedia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.socialvista.socialmedia.models.User;

public interface UserRepository extends JpaRepository<User,Integer> {
    
    public User findByEmail(String email);

    @Query("select u from User u where u.firstname LIKE %:query% OR u.lastname LIKE %:query% OR u.email LIKE %:query%")
    public List<User>searchUser(@Param("query") String query);
}
