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
    private Long id;

    @Column(name = "academic_year")
    private Integer academicYear;

    @Column(name = "session")
    private String session;

    @Column(name = "year_of_study")
    private Integer yearOfStudy;

    @Column(name = "faculty")
    private String faculty;

    @Column(name = "section")
    private String section;

    @Column(name = "course")
    private String course;

    @Column(name = "teacher")
    private String teacher;

    @Column(name = "number_of_seats")
    private Integer numberOfSeats;

    @Column(name = "date")
    private String date;

    private ExamEntity(
            Long id,
            Integer academicYear,
            String session,
            Integer yearOfStudy,
            String faculty,
            String section,
            String course,
            String teacher,
            Integer numberOfSeats,
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
