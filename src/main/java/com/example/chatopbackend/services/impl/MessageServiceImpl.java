package com.example.chatopbackend.services.impl;

import com.example.chatopbackend.model.Dtos.MessageDto;
import com.example.chatopbackend.model.mappers.MessageMapper;
import com.example.chatopbackend.repository.MessageDao;
import com.example.chatopbackend.services.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

@RequiredArgsConstructor
@Service
public class MessageServiceImpl implements MessageService {

    private final MessageDao messageDao;
    private final MessageMapper messageMapper;



    @Override
    public MessageDto saveMessage(MessageDto messageDto) {
        messageDto.setCreated_at(Instant.now());
       return messageMapper.fromMessageToDto(messageDao.save(messageMapper.fromDtoToMessage(messageDto)));

    }
}
