package com.exams.backend.service;

import com.exams.backend.entity.ExamEntity;
import com.exams.backend.repo.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamService {
    private ExamRepository repository;

    @Autowired
    public void setExamRepository(ExamRepository repository) {
        this.repository = repository;
    }

    public List<ExamEntity> getAll() {
        return repository.findAll();
    }

    public List<ExamEntity> getAllFiltered(Specification<ExamEntity> examSpec) {
        return repository.findAll(examSpec);
    }

    public ExamEntity createOrUpdate(ExamEntity exam) {
        return repository.save(exam);
    }

    public boolean delete(long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }

        return false;
    }

    public ExamEntity getOneById(long id) {
        return repository.getOne(id);
    }
}
