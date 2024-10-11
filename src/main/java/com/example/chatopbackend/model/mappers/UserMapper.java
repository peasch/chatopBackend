package com.example.chatopbackend.model.mappers;

import com.example.chatopbackend.model.Dtos.UserDto;
import com.example.chatopbackend.model.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Component
@Mapper(componentModel = SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    @Mapping(target="name",source="name")
    @Mapping(target="email",source="email")
    @Mapping(target="password",source="password")
    @Mapping(target="created_at",source="created_at")
    @Mapping(target="updated_at",source="updated_at")
    User fromDtoToUser(UserDto dto) ;

    @Mapping(target="name",source="name")
    @Mapping(target="email",source="email")
    @Mapping(target="password",source="password")
    @Mapping(target="created_at",source="created_at")
    @Mapping(target="updated_at",source="updated_at")
    UserDto fromUserToDto(User dto) ;

}
