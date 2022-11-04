package com.example.vrs.controller;

import com.example.vrs.model.entity.Review;
import com.example.vrs.model.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class ReviewController {
    @Autowired
    private final ReviewRepository repository;

    public ReviewController(ReviewRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/reviews")
    List<Review> retrieveAllReviews() {
        return repository.findAll();
    }

    @PostMapping("/reviews")
    Review createReview(@RequestBody Review newReview) {
        return repository.save(newReview);
    }

    // @PutMapping("/professors/{id}")
    // Professor updateProfessor(@RequestBody Professor newProfessor,
    // @PathVariable("id") Long professorId) {

    // return repository.findById(professorId)
    // .map(professor -> {
    // professor.setFirstName(newProfessor.getFirstName());
    // professor.setLastName(newProfessor.getLastName());
    // return repository.save(professor);
    // })
    // .orElseGet(() -> {
    // newProfessor.setId(professorId);
    // return repository.save(newProfessor);
    // });
    // }

    @DeleteMapping("/reviews/{reviewId}")
    void deleteReview(@PathVariable("reviewId") Long reviewId) {
        repository.deleteById(reviewId);
    }
}
