package com.exams.web.application.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "exam")
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private int academicYear;

    @Column(nullable = false)
    private String session;

    @Column(nullable = false)
    private int yearOfStudy;

    @Column(nullable = false)
    private String faculty;

    @Column(nullable = false)
    private String section;

    @Column(nullable = false)
    private String course;

    @ManyToOne(targetEntity = Teacher.class)
    @JoinColumn
    private Teacher teacher;

    @Column(nullable = false)
    private int numberOfSeats;

    @Column(nullable = false)
    private String date;
}







