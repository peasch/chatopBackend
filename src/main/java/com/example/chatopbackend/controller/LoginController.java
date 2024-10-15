package com.example.chatopbackend.controller;

import com.example.chatopbackend.model.Dtos.UserDto;
import com.example.chatopbackend.services.JWTService;
import com.example.chatopbackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class LoginController {

    public JWTService jwtService;

    public LoginController(JWTService jwtService) {
        this.jwtService = jwtService;
    }

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

    @PostMapping("/login")
    public ResponseEntity getToken(@RequestBody UserDto userDto) {
        System.out.println(userDto);

        return new ResponseEntity("ok", HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity   register(@RequestBody UserDto userDto) {
        userService.saveUser(userDto);
        System.out.println(userDto.toString());

        return new ResponseEntity("ok", HttpStatus.OK);
    }

}
