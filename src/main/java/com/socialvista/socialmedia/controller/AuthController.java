package com.socialvista.socialmedia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.socialvista.socialmedia.config.JwtProvider;
import com.socialvista.socialmedia.models.User;
import com.socialvista.socialmedia.repository.UserRepository;
import com.socialvista.socialmedia.request.LoginRequest;
import com.socialvista.socialmedia.service.CustomUserDetailsService;
import com.socialvista.socialmedia.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {
    
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    CustomUserDetailsService customUserDetailsService;

     @PostMapping("/users/createUser")
    public AuthResponse createUser(@RequestBody User user) throws Exception {

        User isUserExist = userRepository.findByEmail(user.getEmail());

        if(isUserExist != null){
            throw new Exception("User already exists with this email");
        }

        User newUser = new User();
        
        newUser.setFirstname(user.getFirstname());
        newUser.setLastname(user.getLastname());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));

        User savedUser = userRepository.save(newUser);

        Authentication authentication = new UsernamePasswordAuthenticationToken(savedUser.getEmail(), savedUser.getPassword());

        String token = JwtProvider.generateToken(authentication);

        AuthResponse res = new AuthResponse(token,"User Registered Successfully");

        return res;
    }

    @PostMapping("/users/login")
    public AuthResponse login(@RequestBody LoginRequest loginRequest){

        Authentication authentication = authenticate(loginRequest.getEmail(),loginRequest.getPassword());

        String token = JwtProvider.generateToken(authentication);

        AuthResponse res = new AuthResponse(token,"LoggedIn Successfully");

        return res;
    }

    private Authentication authenticate(String email, String password) {

       UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);

       if(userDetails == null){
         throw new BadCredentialsException("Invalid username");
       }

       if(!passwordEncoder.matches(password, userDetails.getPassword())){
         throw new BadCredentialsException("Invalid password");
       }

        return new UsernamePasswordAuthenticationToken(userDetails, null,userDetails.getAuthorities());
    }

}
