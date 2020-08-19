package com.exams.backend.dto;

import lombok.Data;

@Data
public class ExamDataDTO {
    private long id;
    private int academicYear;
    private String session;
    private int yearOfStudy;
    private String faculty;
    private String section;
    private String course;
    private String teacher;
    private int numberOfSeats;
    private String date;
}
