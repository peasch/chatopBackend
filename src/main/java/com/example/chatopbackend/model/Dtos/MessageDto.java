package com.example.chatopbackend.model.Dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class MessageDto {

    private Integer id;

    private Integer rental_id;

    private Integer user_id;

    private String message;

    private Instant created_at;

    private Instant updated_at;
}
