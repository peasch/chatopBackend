package com.example.chatopbackend.model.mappers;

import com.example.chatopbackend.model.Dtos.MessageDto;
import com.example.chatopbackend.model.entities.Message;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Component
@Mapper(componentModel = SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MessageMapper {

    MessageDto fromMessageToDto(Message message);

    Message fromDtoToMessage(MessageDto messageDto);

}
