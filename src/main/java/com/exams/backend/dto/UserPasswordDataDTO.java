package com.exams.backend.dto;

import lombok.Data;

@Data
public class UserPasswordDataDTO {
    private String email;
    private String currentPassword;
    private String newPassword;
}
