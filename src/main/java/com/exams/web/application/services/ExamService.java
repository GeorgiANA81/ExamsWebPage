package com.exams.web.application.services;

import com.exams.web.application.models.Exam;
import com.exams.web.application.repositories.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamService {
    private ExamRepository repository;

    @Autowired
    public void setExamRepository(ExamRepository repository) {
        this.repository = repository;
    }

    public List<Exam> getAll() {
        return repository.findAll();
    }

    public Exam createOrUpdate(Exam exam) {
        return repository.save(exam);
    }

    public boolean delete(long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }

        return false;
    }
}
