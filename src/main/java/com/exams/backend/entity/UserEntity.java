package com.exams.backend.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

// lombok
@Data
@Builder
@NoArgsConstructor
// spring
@Entity
@Table(name = "user")
public class UserEntity {
    // marks this as part of the primary key
    @Id
    // tells jpa driver to choose appropriate generator strategy
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 128)
    private String name;
    @Column(unique = true)
    private String email;
    @Column
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    List<RoleEntity> roles;

    public UserEntity(long id, String name, String email, String password, List<RoleEntity> roles) {
        super();

        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }
}
