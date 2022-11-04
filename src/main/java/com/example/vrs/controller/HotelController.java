package com.example.vrs.controller;

import com.example.vrs.model.entity.Hotel;
import com.example.vrs.model.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class HotelController {
    @Autowired
    private final HotelRepository repository;

    public HotelController(HotelRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/hotels")
    List<Hotel> retrieveAllHotels() {
        return repository.findAll();
    }

    @PostMapping("/hotels")
    Hotel createHotel(@RequestBody Hotel newHotel) {
        return repository.save(newHotel);
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

    @DeleteMapping("/hotels/{id}")
    void deleteHotel(@PathVariable("id") Long id) {
        repository.deleteById(id);
    }
}