package com.exams.web.application.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
public class Exam
{
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

    @Column(nullable = false)
    private String teacher;

    @Column(nullable = false)
    private int numberOfSeats;

    @Column(nullable = false)
    private String date;

    public Exam(int academicYear, String session, int yearOfStudy, String faculty, String section, String course, String teacher, int numberOfSeats, String date)
    {
        this.academicYear = academicYear;
        this.session = session;
        this.yearOfStudy = yearOfStudy;
        this.faculty = faculty;
        this.section = section;
        this.course = course;
        this.teacher = teacher;
        this.numberOfSeats = numberOfSeats;
        this.date = date;
    }
}







