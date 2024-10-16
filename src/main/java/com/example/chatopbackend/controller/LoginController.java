package com.example.chatopbackend.controller;

import com.example.chatopbackend.model.Dtos.UserDto;
import com.example.chatopbackend.services.JWTService;
import com.example.chatopbackend.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.ResponseEntity.ok;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequiredArgsConstructor
public class LoginController {

    public final JWTService jwtService;

    private final UserService userService;



    @PostMapping("/login")
    public ResponseEntity login(@RequestBody UserDto userDto) {

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
            Map<Object, Object> model = new HashMap<>();

            model.put("message", "User registered successfully");
            model.put("token",jwtService.generateToken(saved));
            return ok(model);
            //return new ResponseEntity(jwtService.generateToken(saved), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("email :" + userDto.getEmail() + " , already used");
            return new ResponseEntity("email already used", HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("/me")
    public ResponseEntity getMe(@RequestBody UserDto userDto) {
        return new ResponseEntity(jwtService.generateToken(userService.getUserByEmail(userDto.getEmail())), HttpStatus.OK);
    }

}
