package com.exams.web.application.services;

import com.exams.web.application.models.Student;
import com.exams.web.application.repositories.StudentRepository;
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

    public List<Student> getAll() {
        return repository.findAll();
    }

    public Student getOne(String username) {
        return repository.getOne(username);
    }

    public Student createOrUpdate(Student student) {
        return repository.save(student);
    }
}
