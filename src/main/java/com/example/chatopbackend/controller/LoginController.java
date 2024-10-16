package com.example.chatopbackend.controller;

import com.example.chatopbackend.model.Dtos.UserDto;
import com.example.chatopbackend.services.JWTService;
import com.example.chatopbackend.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequiredArgsConstructor
public class LoginController {

    public final JWTService jwtService;

    private final UserService userService;

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

        if (userService.getUserByEmail(userDto.getEmail()) != null) {
            System.out.println(userDto);
            return new ResponseEntity(jwtService.generateToken(userDto), HttpStatus.OK);
        } else {
            System.out.println(userDto.getEmail() + " does not exist");
            return new ResponseEntity("email does not exist", HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody UserDto userDto) {
        try {
            userService.saveUser(userDto);
            UserDto saved = userService.getUserByEmail(userDto.getEmail());
            System.out.println(saved.toString());
            return new ResponseEntity(jwtService.generateToken(saved), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("email :" + userDto.getEmail() + " , already used");
            return new ResponseEntity("email already used", HttpStatus.FORBIDDEN);
        }
    }

}
