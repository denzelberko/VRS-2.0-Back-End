package com.example.vrs.controller;

import com.example.vrs.controller.dto.AttractionDto;
import com.example.vrs.controller.exceptions.DestinationNotFoundException;
import com.example.vrs.model.entity.Attraction;
import com.example.vrs.model.entity.Destination;
import com.example.vrs.model.repository.AttractionRepository;
import com.example.vrs.model.repository.DestinationRepository;
import com.fasterxml.jackson.databind.annotation.JsonAppend.Attr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class AttractionController {
    @Autowired
    private final AttractionRepository repository;

    @Autowired
    private DestinationRepository destinationRepository;

    public AttractionController(AttractionRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/attractions")
    Attraction createAttraction(@RequestBody AttractionDto attractionDto) {
        Attraction newAttraction = new Attraction();
        newAttraction.setId(attractionDto.getId());
        newAttraction.setName(attractionDto.getName());
        newAttraction.setType(attractionDto.getType());
        newAttraction.setCost(attractionDto.getCost());
        newAttraction.setDescription(attractionDto.getDescription());
        newAttraction.setHoursToVisit(attractionDto.getHoursToVisit());
        newAttraction.setImageURL(attractionDto.getImageURL());
        Destination destination = destinationRepository.findById(attractionDto.getDestinationId()).orElseThrow(
                () -> new DestinationNotFoundException(attractionDto.getDestinationId()));
        newAttraction.setDestination(destination);
        return repository.save(newAttraction);
    }
}
