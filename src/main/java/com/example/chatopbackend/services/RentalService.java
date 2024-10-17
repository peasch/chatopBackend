package com.example.chatopbackend.services;

import com.example.chatopbackend.model.Dtos.RentalDto;

import java.util.List;

public interface RentalService {

    RentalDto save(RentalDto rentalDto);

    RentalDto findById(Integer id);

    List<RentalDto> findAll();
}
