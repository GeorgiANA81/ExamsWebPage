package com.exams.web.application.controllers;

import com.exams.web.application.models.Student;
import com.exams.web.application.services.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class StudentController {
    private StudentService service;

    @Autowired
    public void setService(StudentService service) {
        this.service = service;
    }

    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);


    @GetMapping("/users")
    public ResponseEntity<List<Student>> getUsers() {
        logger.info("Get all users request");
        List<Student> result = service.getAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/users/{name}")
    public ResponseEntity<Student> getUser(@PathVariable("name") String name) {
        logger.info("Get user by name: {} request", name);

        Student result = service.getOne(name);

        // create the response depending on the result
        if (result != null) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/users")
    public ResponseEntity<Student> createUser(@RequestBody Student student) {
        Student result = service.createOrUpdate(student);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
}
