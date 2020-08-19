package com.exams.backend.rest;

import com.exams.backend.dto.StudentDataDTO;
import com.exams.backend.dto.StudentResponseDTO;
import com.exams.backend.entity.StudentEntity;
import com.exams.backend.service.StudentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/student")
public class StudentRest {
    private StudentService studentService;
    private ModelMapper modelMapper;

    @Autowired
    public void setService(StudentService studentService) {
        this.studentService = studentService;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @GetMapping("/")
    public ResponseEntity<List<StudentResponseDTO>> getAll() {
        List<StudentResponseDTO> result = studentService.getAll()
                .stream()
                .map(studentEntity -> modelMapper.map(studentEntity, StudentResponseDTO.class))
                .collect(Collectors.toList());

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{email}")
    public ResponseEntity<StudentResponseDTO> getUser(@PathVariable("email") String email) {
        StudentResponseDTO result = modelMapper.map(studentService.getOneByEmail(email), StudentResponseDTO.class);

        // create the response depending on the result
        if (result != null) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/")
    public ResponseEntity<StudentResponseDTO> createUser(@RequestBody StudentDataDTO student) {
        StudentResponseDTO result = modelMapper.map(
                studentService.createOrUpdate(
                        modelMapper.map(student, StudentEntity.class)
                ),
                StudentResponseDTO.class
        );

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
}
