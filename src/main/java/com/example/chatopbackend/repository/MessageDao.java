package com.example.chatopbackend.repository;

import com.example.chatopbackend.model.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageDao extends JpaRepository<Message, Integer> {

}
