package com.example.vrs.controller;

import com.example.vrs.model.entity.Attraction;
import com.example.vrs.model.repository.AttractionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class AttractionController {
    @Autowired
    private final AttractionRepository repository;

    public AttractionController(AttractionRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/attractions")
    List<Attraction> retrieveAllAttractions() {
        return repository.findAll();
    }

    @PostMapping("/attractions")
    Attraction createAttraction(@RequestBody Attraction newAttraction) {
        return repository.save(newAttraction);
    }

    // @PutMapping("/courses/{code}")
    // Course updateCourse(@RequestBody CourseDto courseDto, @PathVariable("code")
    // String courseCode) {
    // return repository.findById(courseCode)
    // .map(course -> {
    // course.setName(courseDto.getName());
    // Professor professor =
    // professorRepository.findById(courseDto.getProfessorId()).orElseThrow(
    // () -> new ProfessorNotFoundException(courseDto.getProfessorId()));
    // course.setProfessor(professor);
    // return repository.save(course);
    // })
    // .orElseGet(() -> {
    // Course newCourse = new Course();
    // newCourse.setCode(courseCode);
    // Professor professor =
    // professorRepository.findById(courseDto.getProfessorId()).orElseThrow(
    // () -> new ProfessorNotFoundException(courseDto.getProfessorId()));
    // newCourse.setProfessor(professor);
    // return repository.save(newCourse);
    // });
    // }

    @DeleteMapping("/attractions/{id}")
    void deleteAttraction(@PathVariable("id") Long id) {
        repository.deleteById(id);
    }
}
