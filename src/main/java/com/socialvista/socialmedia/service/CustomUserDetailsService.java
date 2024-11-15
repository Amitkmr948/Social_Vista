package com.socialvista.socialmedia.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.GrantedAuthority;

import com.socialvista.socialmedia.models.User;
import com.socialvista.socialmedia.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       
        User user = userRepository.findByEmail(username);

        if(user == null){
            throw new UsernameNotFoundException("User is not found with email" + username);
        }

      List<GrantedAuthority>authorities = new ArrayList<>();

       return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),authorities);
    }
    
}
