package com.example.chatopbackend.model.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
@Data
@Entity
@Table(name = "rentals", schema = "sys")
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "surface", precision = 10)
    private BigDecimal surface;

    @Column(name = "price", precision = 10)
    private BigDecimal price;

    @Column(name = "picture")
    private String picture;

    @Column(name = "description", length = 2000)
    private String description;

    @Column(name = "owner_id", nullable = false)
    private Integer ownerId;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

}