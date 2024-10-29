package com.example.chatopbackend.config;

import com.example.chatopbackend.model.Dtos.UserDto;
import com.example.chatopbackend.repository.UserDao;
import com.example.chatopbackend.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDto userdto = userService.getUserByEmail(username);

        if(userdto != null) {
            return new User(userdto.getEmail(), userdto.getPassword(), new ArrayList<>());
        }
        return null;
    }
    
}
