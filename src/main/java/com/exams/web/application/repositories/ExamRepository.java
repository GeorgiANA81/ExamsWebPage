package com.exams.web.application.repositories;

import com.exams.web.application.models.Exam;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ExamRepository extends CrudRepository<Exam, Long> {

}
