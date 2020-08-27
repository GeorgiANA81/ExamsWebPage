package com.exams.backend.rest;

import com.exams.backend.dto.ExamDataDTO;
import com.exams.backend.dto.ExamResponseDTO;
import com.exams.backend.entity.ExamEntity;
import com.exams.backend.service.ExamService;
import com.exams.backend.spec.ExamSpec;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/exam")
public class ExamRest {
    private ExamService examService;
    private ModelMapper modelMapper;

    @Autowired
    public void setExamService(ExamService examService) {
        this.examService = examService;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ResponseEntity<List<ExamResponseDTO>> showAll() {
        List<ExamResponseDTO> exams = examService.getAll()
                .stream()
                .map(examEntity -> modelMapper.map(examEntity, ExamResponseDTO.class))
                .collect(Collectors.toList());

        return new ResponseEntity<>(exams, HttpStatus.OK);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<ExamResponseDTO>> showAllFiltered(ExamSpec examSpec) {
        List<ExamResponseDTO> exams = examService.getAllFiltered(examSpec)
                .stream()
                .map(examEntity -> modelMapper.map(examEntity, ExamResponseDTO.class))
                .collect(Collectors.toList());

        return new ResponseEntity<>(exams, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<ExamResponseDTO> create(@RequestBody ExamDataDTO exam) {
        ExamResponseDTO result = modelMapper.map(
                examService.createOrUpdate(
                        modelMapper.map(exam, ExamEntity.class)
                ),
                ExamResponseDTO.class
        );

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExamResponseDTO> updateExam(@RequestBody ExamDataDTO exam, @PathVariable Long id) {
        if (exam.getId() != id) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        ExamResponseDTO result = modelMapper.map(
                examService.createOrUpdate(
                        modelMapper.map(exam, ExamEntity.class)
                ),
                ExamResponseDTO.class
        );

        return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    @CrossOrigin(origins = {"*"}, methods = {RequestMethod.DELETE})
    public ResponseEntity<String> delete(@RequestParam("id") long id) {
        if (examService.delete(id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
