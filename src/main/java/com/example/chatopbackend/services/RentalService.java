package com.example.chatopbackend.services;

import com.example.chatopbackend.model.Dtos.RentalDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface RentalService {

    RentalDto save(String name, int surface, int price, MultipartFile picture,String description,Integer ownerId) throws IOException;

    RentalDto findById(Integer id);

    List<RentalDto> findAll();

    RentalDto updateRental (Integer id,String name, int surface, int price, String description) throws IOException;
}
