package com.exams.backend.dto;

import lombok.Data;

@Data
public class UserResponseDTO {
    private long id;
    private String name;
    private String email;
    private String password;
}
