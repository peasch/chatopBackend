package com.example.chatopbackend.model.mappers;

import com.example.chatopbackend.model.Dtos.UserDto;
import com.example.chatopbackend.model.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Component
@Mapper(componentModel = SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {


    User fromDtoToUser(UserDto dto) ;

    UserDto fromUserToDto(User dto) ;

}
