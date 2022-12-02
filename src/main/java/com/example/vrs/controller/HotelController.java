package com.example.vrs.controller;

import com.example.vrs.model.entity.Hotel;
import com.example.vrs.model.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class HotelController {
    @Autowired
    private final HotelRepository repository;

    public HotelController(HotelRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/hotels")
    Hotel createHotel(@RequestBody Hotel newHotel) {
        return repository.save(newHotel);
    }
}