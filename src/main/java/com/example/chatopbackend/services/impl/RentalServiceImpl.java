package com.example.chatopbackend.services.impl;

import com.example.chatopbackend.model.Dtos.RentalDto;
import com.example.chatopbackend.model.entities.Rental;
import com.example.chatopbackend.model.mappers.RentalMapper;
import com.example.chatopbackend.repository.RentalDao;
import com.example.chatopbackend.services.RentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RentalServiceImpl implements RentalService {

    private final RentalDao rentalDao;
    private final RentalMapper rentalMapper;

    @Override
    public RentalDto save(RentalDto rentalDto) {

        Rental rental = rentalMapper.fromDtoToRental(rentalDto);

        rental.setCreatedAt(Instant.now());
        rentalDao.save(rental);
    return rentalMapper.fromRentalToDto(rental);

    }

    @Override
    public RentalDto findById(Integer id) {
        return rentalMapper.fromRentalToDto(rentalDao.findById(id).get());
    }

    @Override
    public List<RentalDto> findAll() {
        List<Rental> rentals = rentalDao.findAll();
        System.out.println("rental DAO :"+ rentals);
        List<RentalDto> rentalDtos = new ArrayList<>();
        rentals.forEach(rental -> rentalDtos.add(rentalMapper.fromRentalToDto(rental)));
        System.out.println("rentalsDTO : "+rentalDtos);
        return rentalDtos;
    }
}



