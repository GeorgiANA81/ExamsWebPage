package com.exams.backend.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

// lombok
@Data
@Builder
@NoArgsConstructor
// spring
@Entity
@Table(name = "exam")
public class ExamEntity {
    // marks this as part of the primary key
    @Id
    // tells jpa driver to choose appropriate generator strategy
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, name = "academic_year")
    private int academicYear;

    @Column(nullable = false, name = "session")
    private String session;

    @Column(nullable = false, name = "year_of_study")
    private int yearOfStudy;

    @Column(nullable = false, name = "faculty")
    private String faculty;

    @Column(nullable = false, name = "section")
    private String section;

    @Column(nullable = false, name = "course")
    private String course;

    @Column(nullable = false, name = "teacher")
    private String teacher;

    @Column(nullable = false, name = "number_of_seats")
    private int numberOfSeats;

    @Column(nullable = false, name = "date")
    private String date;

    private ExamEntity(
            long id,
            int academicYear,
            String session,
            int yearOfStudy,
            String faculty,
            String section,
            String course,
            String teacher,
            int numberOfSeats,
            String date
    ) {
        super();

        this.id = id;
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
