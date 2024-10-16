package com.example.chatopbackend.services.impl;

import com.example.chatopbackend.model.Dtos.UserDto;
import com.example.chatopbackend.model.mappers.UserMapper;
import com.example.chatopbackend.repository.UserDao;
import com.example.chatopbackend.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import javax.validation.ValidationException;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {


    private final UserDao userDao;
    @Qualifier("userMapper")
    private final UserMapper mapper;

    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
    public List<UserDto> getAllUsers() {
        return userDao.findAll().stream().map(mapper::fromUserToDto).toList();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
    public UserDto getUserById(int id) {
        return mapper.fromUserToDto(userDao.findById(id));
    }

    @Override
    public UserDto getUserByEmail(String email) {
        if (checkEmail(email)) {
            return mapper.fromUserToDto(userDao.findByEmail(email));
        }else{
            return null;
        }
    }

    @Override
    public UserDto saveUser(UserDto userDto) {
        if (!this.checkEmail(userDto.getEmail())) {
            userDto.setCreated_at(new Date(Date.from(Instant.now()).getTime()));
            userDao.save(mapper.fromDtoToUser(userDto));
            return mapper.fromUserToDto(userDao.findById(userDto.getId()));
        } else {
            throw new ValidationException("already used email");
        }


    }

    private boolean checkEmail(String email) {
        return userDao.findByEmail(email) != null;

    }
}
