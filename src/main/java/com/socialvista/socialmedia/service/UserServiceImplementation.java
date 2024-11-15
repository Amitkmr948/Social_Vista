package com.socialvista.socialmedia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exception.ResourceNotFoundException;
import com.socialvista.socialmedia.config.JwtProvider;
import com.socialvista.socialmedia.models.User;
import com.socialvista.socialmedia.repository.UserRepository;

@Service
public class UserServiceImplementation implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User createUser(User user) {
        User newUser = new User();
        
        newUser.setFirstname(user.getFirstname());
        newUser.setLastname(user.getLastname());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword());

        User savedUser = userRepository.save(newUser);

        return savedUser;
    }

    @Override
    public User findUserById(Integer id) {
         User user = userRepository.findById(id)
         .orElseThrow(()->
         new ResourceNotFoundException("User is not found with the id : " + id)
         );

        return user;
    }

    @Override
    public User findUserByEmail(String email) {
       
        User user = userRepository.findByEmail(email);

        return user;

    }

    @Override
    public User followUser(Integer userId1, Integer userId2) {
       
        User user1 = userRepository.findById(userId1)
        .orElseThrow(()->
        new ResourceNotFoundException("User1 is not found with the id : " + userId1)
        );

        User user2 = userRepository.findById(userId2)
        .orElseThrow(()->
        new ResourceNotFoundException("User2 is not found with the id : " + userId2)
        );

        user2.getFollowers().add(user1.getId());
        user1.getFollowings().add(user2.getId());

        userRepository.save(user1);
        userRepository.save(user2);

        return user1;

    }

    @Override
    public User updateUser(User Updateduser,Integer id) {

        User user = userRepository.findById(id)
        .orElseThrow(()->
        new ResourceNotFoundException("User is not found with the id : " + id)
      );
      
      if(Updateduser.getFirstname() != null){
         user.setFirstname(Updateduser.getFirstname());
      }
      if(Updateduser.getLastname() != null){
         user.setLastname(Updateduser.getLastname());
      }
      if(Updateduser.getEmail() != null){
         user.setEmail(Updateduser.getEmail());
      }
      if(Updateduser.getPassword() != null){
         user.setPassword(Updateduser.getPassword());
      }
      
      User savedUser = userRepository.save(user);
     
     return savedUser;
    }

    @Override
    public List<User> searchUser(String query) {
        
        return userRepository.searchUser(query);
    }

    @Override
    public User findUserByJwt(String Jwt) {
       
        String email = JwtProvider.getEmailFromJwtToken(Jwt);
        
        User user = userRepository.findByEmail(email);
        
        return user;
    }
    
}
