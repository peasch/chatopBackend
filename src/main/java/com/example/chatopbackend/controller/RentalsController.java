package com.example.chatopbackend.controller;

import com.example.chatopbackend.model.Dtos.RentalDto;
import com.example.chatopbackend.model.entities.Rental;
import com.example.chatopbackend.services.RentalService;
import com.example.chatopbackend.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
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
    private final UserService userService;

    @GetMapping
    public ResponseEntity getAllRentals() {

        Map<Object, Object> model = new HashMap<>();

        model.put("rentals", rentalService.findAll());
        return ok(model);

    }

    @PostMapping
    public ResponseEntity createRental(@Valid @RequestParam("name") String name,
                                       @Valid @RequestParam("surface") int surface,
                                       @Valid @RequestParam("price") int price,
                                       @Valid @RequestParam("picture") MultipartFile picture,
                                       @Valid @RequestParam("description") String description,
                                       @AuthenticationPrincipal Jwt principal ) {

            Integer owner_id=userService.getUserByEmail(principal.getClaimAsString("sub")).getId();
        try {

            RentalDto rentalDto =rentalService.save(name,surface,price,picture,description,owner_id);

            RentalDto rentalDtoreturned = rentalService.findById(rentalDto.getId());
            System.out.println(rentalDto);
            Map<Object, Object> model = new HashMap<>();
            model.put("message", "Rental created !");
            model.put("rental", rentalDto);
            return ok(model);
        }catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity("problem with rental", HttpStatus.UNAUTHORIZED);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity updateRental(@Valid @RequestParam("name") String name,
                                       @Valid @RequestParam("surface") int surface,
                                       @Valid @RequestParam("price") int price,
                                       @Valid @RequestParam("description") String description,
                                       @PathVariable int id) {
        
        try{
           RentalDto updatedRental = rentalService.updateRental(id,name,surface,price,description);
            Map<Object, Object> model = new HashMap<>();
            model.put("message", "Rental updated !");
            model.put("rental", updatedRental);
            return ok(model);
        }catch (Exception e) {
            return new ResponseEntity("problem with rental", HttpStatus.FORBIDDEN);
        }

    }


    @GetMapping("/{id}")
    public ResponseEntity getRentalById(@PathVariable Integer id) {
        return new ResponseEntity<>(rentalService.findById(id), HttpStatus.OK);
    }


}
