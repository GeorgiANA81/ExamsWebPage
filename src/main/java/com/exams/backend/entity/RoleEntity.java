package com.exams.backend.entity;

import org.springframework.security.core.GrantedAuthority;

public enum RoleEntity implements GrantedAuthority {
    ROLE_STUDENT, ROLE_TEACHER;

    public String getAuthority() {
        return name();
    }
}
