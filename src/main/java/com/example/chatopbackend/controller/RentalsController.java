package com.example.chatopbackend.controller;

import com.example.chatopbackend.model.Dtos.RentalDto;
import com.example.chatopbackend.model.entities.Rental;
import com.example.chatopbackend.services.RentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.ResponseEntity.ok;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/rentals")
public class RentalsController {
    private final RentalService rentalService;

    @GetMapping
    public ResponseEntity getAllRentals() {

        Map<Object, Object> model = new HashMap<>();

        model.put("rentals", rentalService.findAll());
        return ok(model);

    }

    @PostMapping(name = "/create")
    public ResponseEntity createRental(@RequestBody RentalDto rental) {
        rental.setPicture("https://blog.technavio.org/wp-content/uploads/2018/12/Online-House-Rental-Sites.jpg");
        try {
            rentalService.save(rental);
            RentalDto rentalDto = rentalService.findById(rental.getId());
            System.out.println(rentalDto);
            Map<Object, Object> model = new HashMap<>();
            model.put("rental", rentalDto);
            return ok(model);
        }catch (Exception e) {
            return new ResponseEntity("problem with rental", HttpStatus.FORBIDDEN);
        }
    }

}
