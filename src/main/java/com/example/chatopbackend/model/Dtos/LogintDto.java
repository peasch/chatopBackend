package com.example.chatopbackend.model.Dtos;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class LogintDto
{
    @NotEmpty
    private String email;
    @NotEmpty
    private String password;
}
