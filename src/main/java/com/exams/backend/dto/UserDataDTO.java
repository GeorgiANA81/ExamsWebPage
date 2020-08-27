package com.exams.backend.dto;

import lombok.Data;

@Data
public class UserDataDTO {
    private Long id;
    private String name;
    private String email;
    private String password;
}
