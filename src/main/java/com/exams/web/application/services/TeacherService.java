package com.exams.web.application.services;

import com.exams.web.application.models.Teacher;
import com.exams.web.application.repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {
    private TeacherRepository repository;

    @Autowired
    public void setTeacherRepository(TeacherRepository repository) {
        this.repository = repository;
    }

    public List<Teacher> getAll() {
        return repository.findAll();
    }

    public Teacher getOne(long teacher) {
        return repository.getOne(teacher);
    }
}
