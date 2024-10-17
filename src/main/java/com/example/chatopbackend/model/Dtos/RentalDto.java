package com.example.chatopbackend.model.Dtos;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class RentalDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String name;

    private BigDecimal surface;

    private BigDecimal price;

    private String picture;

    private String description;

    private Integer ownerId;

    private Instant createdAt;

    private Instant updatedAt;
}
