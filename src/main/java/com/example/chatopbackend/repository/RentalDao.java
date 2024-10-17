package com.example.chatopbackend.repository;

import com.example.chatopbackend.model.entities.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RentalDao extends JpaRepository<Rental, Integer> {

   List<Rental> findAll();

   Rental findById(int id);

}
