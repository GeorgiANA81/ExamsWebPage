package com.exams.web.application.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private StudentRepository studentRepository;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);


    @GetMapping("/users")
    public List<User> getUsers(){
        logger.info("Get all users request");
        return userRepository.findAll();
    }

    @GetMapping("/users/{name}")
    public User getUser(@PathVariable("name") String name){
        logger.info("Get user by name: {} request",name);

        return userRepository.findUserName(name)
                .orElseThrow(() ->{
                    logger.error("User with name: {} not found",name);
                    return new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            String.format("User with name: %s not found",name)
                    );
                });
    }
}
