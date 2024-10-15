package com.example.chatopbackend.controller;

import com.example.chatopbackend.model.Dtos.UserDto;
import com.example.chatopbackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public String getUser() {

        return "hello and welcome user";
    }

    @GetMapping("/admin")
    public String getAdmin() {

        return "hello and welcome admin";
    }

    @GetMapping("/login")
    public String getUserLogin(@RequestBody UserDto userDto) {
        System.out.println(userDto.toString());
        return "hello and welcome user";
    }

    @PostMapping("/register")
    public ResponseEntity   register(@RequestBody UserDto userDto) {
        userService.saveUser(userDto);
        System.out.println(userDto.toString());

        return new ResponseEntity("ok", HttpStatus.OK);
    }

}
