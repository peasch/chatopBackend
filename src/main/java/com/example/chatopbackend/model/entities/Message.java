package com.example.chatopbackend.model.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;

@Data
@Entity
@Table(name = "messages", schema = "sys")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "rental_id")
    private Integer rental_id;

    @Column(name = "user_id")
    private Integer user_id;

    @Column(name = "message", length = 2000)
    private String message;

    @Column(name = "created_at")
    private Instant created_at;

    @Column(name = "updated_at")
    private Instant updated_at;

}