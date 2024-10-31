package com.example.chatopbackend.services;


import com.example.chatopbackend.model.Dtos.UserDto;


public interface UserService {


    UserDto getUserById(int id);

    UserDto getUserByEmail(String email);

    UserDto saveUser(UserDto userDto);
}
