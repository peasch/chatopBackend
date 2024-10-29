package com.example.chatopbackend.model.Dtos;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserDto  implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;

    private String name;

    private String email;

    private String password;

    private Date created_at;

    private Date updated_at;
}
