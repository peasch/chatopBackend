package com.example.chatopbackend.model.mappers;

import com.example.chatopbackend.model.Dtos.RentalDto;
import com.example.chatopbackend.model.entities.Rental;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Component
@Mapper(componentModel = SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RentalMapper {

    Rental fromDtoToRental(RentalDto rentalDto);

    RentalDto fromRentalToDto(Rental rental);

}
