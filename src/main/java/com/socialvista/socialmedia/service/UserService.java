package com.socialvista.socialmedia.service;

import java.util.List;

import com.socialvista.socialmedia.models.User;

public interface UserService {
   public User createUser(User user);

   public User findUserById(Integer id);

   public User findUserByEmail(String email);

   public User followUser(Integer userId1,Integer userId2);

   public User updateUser(User user, Integer id);

   public List<User>searchUser(String query);

   public User findUserByJwt(String Jwt);
}
