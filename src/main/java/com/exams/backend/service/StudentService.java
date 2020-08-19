package com.exams.backend.service;

import com.exams.backend.entity.StudentEntity;
import com.exams.backend.repo.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private StudentRepository repository;

    @Autowired
    public void setStudentRepository(StudentRepository repository) {
        this.repository = repository;
    }

    public List<StudentEntity> getAll() {
        return repository.findAll();
    }

    public StudentEntity createOrUpdate(StudentEntity student) {
        return repository.save(student);
    }

    public StudentEntity getOneById(long id) {
        return repository.getOne(id);
    }

    public StudentEntity getOneByEmail(String email) {
        return repository.getOneByEmail(email);
    }
}
