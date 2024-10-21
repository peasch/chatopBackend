package com.example.chatopbackend.services;

import com.example.chatopbackend.model.Dtos.MessageDto;

import java.util.List;

public interface MessageService {

    MessageDto saveMessage(MessageDto messageDto);

}
