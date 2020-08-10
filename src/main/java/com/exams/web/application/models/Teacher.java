package com.exams.web.application.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String name;
    @Column
    private String email;
    @Column
    private String password;

    @Column
    private int academicYear;
}
