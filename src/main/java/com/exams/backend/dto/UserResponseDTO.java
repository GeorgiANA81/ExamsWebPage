package com.exams.backend.dto;

import com.exams.backend.entity.RoleEntity;
import lombok.Data;

import java.util.List;

@Data
public class UserResponseDTO {
    private long id;
    private String name;
    private String email;
    private Integer academicYear;
    private String token;
    private List<RoleEntity> roles;
}
