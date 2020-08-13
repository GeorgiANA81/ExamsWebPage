package com.exams.backend.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

// lombok
@Data
@Builder
@NoArgsConstructor
// spring
@Entity
@Table(name = "student")
public class StudentEntity {
    // marks this as part of the primary key
    @Id
    // tells jpa driver to choose appropriate generator strategy
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    // sample column configuration
    @Column(length = 128)
    private String name;
    @Column(unique = true)
    private String email;
    @Column
    private String password;


    private StudentEntity(long id, String name, String email, String password) {
        super();

        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String encodePassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());

        byte[] digest = md.digest();
        return DatatypeConverter.printHexBinary(digest).toUpperCase();
    }
}
