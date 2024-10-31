package com.example.chatopbackend.services;

import com.example.chatopbackend.model.Dtos.MessageDto;

public interface MessageService {

    MessageDto saveMessage(MessageDto messageDto);

}
