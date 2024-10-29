package com.example.chatopbackend.controller;

import com.example.chatopbackend.model.Dtos.UserDto;
import com.example.chatopbackend.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.ResponseEntity.ok;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @Operation(summary = "user by id method", description = "clic here to get user details, with its id")
    @ApiResponse(responseCode = "200", description = "You're logged in")
    @ApiResponse(responseCode = "500", description = "error")
    @GetMapping("/{id}")
    public ResponseEntity getUserById(@PathVariable int id) {
        try {
            UserDto user = userService.getUserById(id);
            Map<Object, Object> model = new HashMap<>();
            model.put("message", "Rental updated !");
            model.put("user : " + id, user);
            return ok(model);
        } catch (Exception e) {
            return new ResponseEntity("problem with user", HttpStatus.UNAUTHORIZED);
        }
    }
}
