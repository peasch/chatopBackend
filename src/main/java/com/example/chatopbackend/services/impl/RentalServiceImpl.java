package com.example.chatopbackend.services.impl;

import com.example.chatopbackend.model.Dtos.RentalDto;
import com.example.chatopbackend.model.entities.Rental;
import com.example.chatopbackend.model.mappers.RentalMapper;
import com.example.chatopbackend.repository.RentalDao;
import com.example.chatopbackend.services.FileUploadService;
import com.example.chatopbackend.services.RentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RentalServiceImpl implements RentalService {

    private final RentalDao rentalDao;
    private final RentalMapper rentalMapper;
    private final FileUploadService fileUploadService;

    @Override
    public RentalDto save(String name, int surface, int price, MultipartFile picture, String description, Integer owner_id) throws IOException {
        RentalDto rentalDto = new RentalDto();
        rentalDto.setName(name);
        rentalDto.setDescription(description);
        rentalDto.setPrice(BigDecimal.valueOf(price));
        rentalDto.setSurface(BigDecimal.valueOf(surface));
        String pictureString = fileUploadService.uploadFile(picture);
        rentalDto.setPicture(pictureString);
        rentalDto.setOwner_id(owner_id);
        rentalDto.setCreated_at(Instant.now());

        return rentalMapper.fromRentalToDto(rentalDao.save(rentalMapper.fromDtoToRental(rentalDto)));

    }

    @Override
    public RentalDto findById(Integer id) {
        return rentalMapper.fromRentalToDto(rentalDao.findById(id).get());
    }

    @Override
    public List<RentalDto> findAll() {
        List<Rental> rentals = rentalDao.findAll();
        List<RentalDto> rentalDtos = new ArrayList<>();
        rentals.forEach(rental -> rentalDtos.add(rentalMapper.fromRentalToDto(rental)));

        return rentalDtos;
    }

    @Override
    public RentalDto updateRental(Integer id, String name, int surface, int price, String description) throws IOException {
        RentalDto rentalDto = findById(id);
        rentalDto.setName(name);
        rentalDto.setUpdated_at(Instant.now());
        rentalDto.setDescription(description);
        rentalDto.setPrice(BigDecimal.valueOf(price));
        rentalDto.setSurface(BigDecimal.valueOf(surface));

        return rentalMapper.fromRentalToDto(
                rentalDao.save(rentalMapper.fromDtoToRental(rentalDto)));

    }


}



