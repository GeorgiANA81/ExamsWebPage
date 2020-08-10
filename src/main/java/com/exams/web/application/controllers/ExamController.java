package com.exams.web.application.controllers;

import com.exams.web.application.exceptions.ExamIdMismatchException;
import com.exams.web.application.models.Exam;
import com.exams.web.application.services.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/exams")
public class ExamController {
    @Value("${spring.application.name}")
    String appName;

    private ExamService service;

    @Autowired
    public void setExamService(ExamService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("java.com.exams.web.application", appName);
        return "home";
    }

    @GetMapping
    public ResponseEntity<List<Exam>> showAll() {
        List<Exam> exams = service.getAll();
        return new ResponseEntity<>(exams, HttpStatus.OK);
    }

    @PostMapping("/exam")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Exam> create(@RequestBody Exam exam) {
        Exam result = service.createOrUpdate(exam);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/exam/{yearOfStudy}")
    private Predicate<Exam> equalStudyYear(@PathVariable int yearOfStudy) {
        return exam -> exam.getYearOfStudy() == yearOfStudy;
    }

    @GetMapping("/exam/{academicYear}")
    private Predicate<Exam> equalAcademicYear(@PathVariable int academicYear) {
        return exam -> exam.getAcademicYear() == academicYear;
    }

    @GetMapping("/exam/{session}")
    private Predicate<Exam> equalSession(@PathVariable String session) {
        return exam -> exam.getSession().equals(session);
    }

    @GetMapping("/exam/{faculty}")
    private Predicate<Exam> equalFaculty(@PathVariable String faculty) {
        return exam -> exam.getFaculty().equals(faculty);
    }

    @GetMapping("/exam/{domain}")
    private Predicate<Exam> equalDomain(@PathVariable String section) {
        return exam -> exam.getSection().equals(section);
    }

    @GetMapping("/exam/{course}")
    private Predicate<Exam> equalCourse(@PathVariable String course) {
        return exam -> exam.getCourse().equals(course);
    }

    @GetMapping("/exam/{teacher}")
    private Predicate<Exam> equalTeacher(@PathVariable String teacher) {
        return exam -> exam.getTeacher().equals(teacher);
    }

    @GetMapping("/exam/{date}")
    private Predicate<Exam> equalDate(@PathVariable String date) {
        return exam -> exam.getDate() == date;
    }

    private ResponseEntity<List<Exam>> filter(List<Predicate<Exam>> options) {
        List<Exam> result = service.getAll()
                .stream()
                .filter(options.stream().reduce(x -> true, Predicate::and))
                .collect(Collectors.toList());

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@RequestParam("id") long id) {
        // use the service to do the work, respond depending on the result
        if (service.delete(id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Exam> updateExam(@RequestBody Exam exam, @PathVariable Long id) throws ExamIdMismatchException {
        if (exam.getId() != id) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(service.createOrUpdate(exam), HttpStatus.OK);
    }
}

