package com.example.chatopbackend.services.impl;

import com.example.chatopbackend.model.Dtos.UserDto;
import com.example.chatopbackend.model.mappers.UserMapper;
import com.example.chatopbackend.repository.UserDao;
import com.example.chatopbackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserMapper mapper;

    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly=true)
    public List<UserDto> getAllUsers() {
        return userDao.findAll().stream().map(mapper::fromUsertoDto).toList();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly=true)
    public UserDto getUserById(int id) {
        return mapper.fromUsertoDto(userDao.findById(id));
    }

}
