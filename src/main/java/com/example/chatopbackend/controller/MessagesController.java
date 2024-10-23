package com.example.chatopbackend.controller;

import com.example.chatopbackend.model.Dtos.MessageDto;
import com.example.chatopbackend.services.MessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.ResponseEntity.ok;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/messages")
public class MessagesController {

    private final MessageService messageService;

    @Operation(summary = "Message post", description = "clic here to send a message to the rental owner")

    @ApiResponse(responseCode = "200", description = "You're logged in")
    @ApiResponse(responseCode = "500", description = "error")
    @PostMapping("")
    public ResponseEntity sendMessage(@RequestBody MessageDto message) {

        Map<Object, Object> model = new HashMap<>();
        model.put("entity", messageService.saveMessage(message));
        model.put("message", "Message send with success");
        return ok(model);

    }

}
