package com.exams.backend.rest;

import com.exams.backend.entity.ExamEntity;
import com.exams.backend.service.ExamService;
import com.exams.backend.spec.ExamSpec;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exam")
public class ExamRest {
    private ExamService examService;

    @Autowired
    public void setExamService(ExamService examService) {
        this.examService = examService;
    }

    @GetMapping
    public ResponseEntity<List<ExamEntity>> showAll() {
        List<ExamEntity> exams = examService.getAll();

        return new ResponseEntity<>(exams, HttpStatus.OK);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<ExamEntity>> showAllFiltered(ExamSpec examSpec) {
        List<ExamEntity> exams = examService.getAllFiltered(examSpec);

        return new ResponseEntity<>(exams, HttpStatus.OK);
    }

    @PostMapping("/exam")
    public ResponseEntity<ExamEntity> create(@RequestBody ExamEntity exam) {
        ExamEntity result = examService.createOrUpdate(exam);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExamEntity> updateExam(@RequestBody ExamEntity exam, @PathVariable Long id) {
        if (exam.getId() != id) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(examService.createOrUpdate(exam), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@RequestParam("id") long id) {
        if (examService.delete(id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
