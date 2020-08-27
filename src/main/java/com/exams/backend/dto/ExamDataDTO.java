package com.exams.backend.dto;

import lombok.Data;

@Data
public class ExamDataDTO {
    private long id;
    private Integer academicYear;
    private String session;
    private Integer yearOfStudy;
    private String faculty;
    private String section;
    private String course;
    private String teacher;
    private Integer numberOfSeats;
    private String date;
}
