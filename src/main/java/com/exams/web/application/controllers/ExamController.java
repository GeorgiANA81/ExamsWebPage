package com.exams.web.application.controllers;

import com.exams.web.application.exceptions.ExamIdMismatchException;
import com.exams.web.application.exceptions.ExamNotFoundException;
import com.exams.web.application.models.Exam;
import com.exams.web.application.repositories.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
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

    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("java.com.exams.web.application", appName);
        return "home";
    }

    @Autowired
    private ExamRepository examRepository;

    @GetMapping
    public Iterable showAll()
    {
        return examRepository.findAll();
    }

    @PostMapping("/exam")
    @ResponseStatus(HttpStatus.CREATED)
    private Exam createExam(@RequestBody Exam newExam)
    {
        return examRepository.save(newExam);
    }

    @GetMapping("/exam/{yearOfStudy}")
    private Predicate<Exam> equalStudyYear(@PathVariable int yearOfStudy)
    {
        return exam -> exam.getYearOfStudy() == yearOfStudy;
    }

    @GetMapping("/exam/{academicYear}")
    private Predicate<Exam> equalAcademicYear(@PathVariable int academicYear)
    {
        return exam -> exam.getAcademicYear() == academicYear;
    }

    @GetMapping("/exam/{session}")
    private Predicate<Exam> equalSession(@PathVariable String session)
    {
        return exam -> exam.getSession() == session;
    }

    @GetMapping("/exam/{faculty}")
    private Predicate<Exam> equalFaculty(@PathVariable String faculty)
    {
        return exam -> exam.getFaculty() == faculty;
    }

    @GetMapping("/exam/{domain}")
    private Predicate<Exam> equalDomain(@PathVariable String section)
    {
        return exam -> exam.getSection() == section;
    }

    @GetMapping("/exam/{course}")
    private Predicate<Exam> equalCourse(@PathVariable String course)
    {
        return exam -> exam.getCourse() == course;
    }

    @GetMapping("/exam/{teacher}")
    private Predicate<Exam> equalTeacher(@PathVariable String teacher)
    {
        return exam -> exam.getTeacher() == teacher;
    }

    @GetMapping("/exam/{date}")
    private Predicate<Exam> equalDate(@PathVariable String date)
    {
        return exam -> exam.getDate() == date;
    }

    private List<Exam> filter(List<Predicate<Exam>> options)
    {
        return examRepository.findAll()
                .stream()
                .filter(options.stream().reduce(x->true, Predicate::and))
                .collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) throws ExamNotFoundException {
        examRepository.findById(id)
                .orElseThrow(ExamNotFoundException::new);
        examRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public Exam updateExam(@RequestBody Exam exam, @PathVariable Long id) throws ExamIdMismatchException, ExamNotFoundException {
        if (exam.getId() != id) {
            throw new ExamIdMismatchException();
        }
        examRepository.findById(id)
                .orElseThrow(ExamNotFoundException::new);
        return examRepository.save(exam);
    }
}

