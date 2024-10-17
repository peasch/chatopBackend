package com.example.chatopbackend.controller;

import com.auth0.jwt.interfaces.Claim;
import com.example.chatopbackend.config.CustomUserDetailsService;
import com.example.chatopbackend.model.Dtos.LogintDto;
import com.example.chatopbackend.model.Dtos.UserDto;
import com.example.chatopbackend.services.JWTService;
import com.example.chatopbackend.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.ResponseEntity.ok;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class LoginController {

    public final JWTService jwtService;

    private final UserService userService;

    private final AuthenticationManager authenticationManager;

    private final CustomUserDetailsService customUserDetailsService;



    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LogintDto logintDto) {
        System.out.println(logintDto);
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(logintDto.getEmail(), logintDto.getPassword()));
            UserDto userLoggedin = userService.getUserByEmail(logintDto.getEmail());
            String token = jwtService.generateToken(userLoggedin);
            Map<Object, Object> model = new HashMap<>();

            model.put("message", "logged in");
            model.put("token",token);
            model.put("user", userLoggedin);
            System.out.println("loggedin : " + userLoggedin);
            return ok(model);

        }catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.UNAUTHORIZED);
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
            model.put("user", saved);
            return ok(model);
            //return new ResponseEntity(jwtService.generateToken(saved), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("email :" + userDto.getEmail() + " , already used");
            return new ResponseEntity("email already used", HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("/me")
    public ResponseEntity getMe(@AuthenticationPrincipal Jwt principal) {

        return new ResponseEntity(userService.getUserByEmail(principal.getClaimAsString("sub")), HttpStatus.OK);

    }

}
