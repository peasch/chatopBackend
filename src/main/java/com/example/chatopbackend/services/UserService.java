package com.example.chatopbackend.services;

import com.example.chatopbackend.model.Dtos.UserDto;

import java.util.List;

public interface UserService {

    List<UserDto> getAllUsers();

    UserDto getUserById(int id);
}
