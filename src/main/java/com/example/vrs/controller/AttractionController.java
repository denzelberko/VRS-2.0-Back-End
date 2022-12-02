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

    @PostMapping("/attractions")
    Attraction createAttraction(@RequestBody Attraction newAttraction) {
        return repository.save(newAttraction);
    }
}
