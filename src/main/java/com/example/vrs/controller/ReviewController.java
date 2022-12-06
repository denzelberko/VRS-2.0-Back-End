package com.example.vrs.controller;

import com.example.vrs.controller.dto.ReviewDto;
import com.example.vrs.controller.exceptions.DestinationNotFoundException;
import com.example.vrs.model.entity.Destination;
import com.example.vrs.model.entity.Review;
import com.example.vrs.model.repository.DestinationRepository;
import com.example.vrs.model.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class ReviewController {
    @Autowired
    private final ReviewRepository repository;

    @Autowired
    private DestinationRepository destinationRepository;

    public ReviewController(ReviewRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/reviews")
    Review createReview(@RequestBody ReviewDto reviewDto) {
        Review newReview = new Review();
        newReview.setReviewId(reviewDto.getReviewId());
        newReview.setRating(reviewDto.getRating());
        newReview.setMessage(reviewDto.getMessage());
        Destination destination = destinationRepository.findById(reviewDto.getDestinationId()).orElseThrow(
                () -> new DestinationNotFoundException(reviewDto.getDestinationId()));
        newReview.setDestination(destination);
        return repository.save(newReview);
    }

}
