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

    @PostMapping("/reviews")
    Review createReview(@RequestBody Review newReview) {
        return repository.save(newReview);
    }

}
