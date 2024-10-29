package com.example.chatopbackend.services;


import com.example.chatopbackend.model.Dtos.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {
    List<UserDto> getAllUsers();

    UserDto getUserById(int id);

    UserDto getUserByEmail(String email);

    UserDto saveUser(UserDto userDto);
}
