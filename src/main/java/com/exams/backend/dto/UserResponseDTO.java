package com.exams.backend.dto;

import lombok.Data;

@Data
public class UserResponseDTO {
    private long id;
    private String name;
    private String email;
    private Integer academicYear;
    private String token;
}
