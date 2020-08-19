package com.exams.backend.dto;

import lombok.Data;

@Data
public class StudentResponseDTO {
    private long id;
    private String name;
    private String email;
    private String password;
}
