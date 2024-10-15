package com.example.chatopbackend.repository;

import com.example.chatopbackend.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserDao extends JpaRepository<User,Integer> {

    List<User> findAll();

    User findById(int id);

    User findByEmail(String email);

    User findByEmailAndPassword(String email, String password);


}
