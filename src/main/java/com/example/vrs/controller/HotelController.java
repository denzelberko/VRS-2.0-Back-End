package com.example.vrs.controller;

import com.example.vrs.controller.dto.HotelDto;
import com.example.vrs.controller.exceptions.DestinationNotFoundException;
import com.example.vrs.model.entity.Destination;
import com.example.vrs.model.entity.Hotel;
import com.example.vrs.model.repository.DestinationRepository;
import com.example.vrs.model.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class HotelController {
    @Autowired
    private final HotelRepository repository;

    @Autowired
    private DestinationRepository destinationRepository;

    public HotelController(HotelRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/hotels")
    Hotel createHotel(@RequestBody HotelDto hotelDto) {
        Hotel newHotel = new Hotel();
        newHotel.setId(hotelDto.getId());
        newHotel.setName(hotelDto.getName());
        newHotel.setRating(hotelDto.getRating());
        newHotel.setCost(hotelDto.getCost());
        Destination destination = destinationRepository.findById(hotelDto.getDestinationId()).orElseThrow(
                () -> new DestinationNotFoundException(hotelDto.getDestinationId()));
        newHotel.setDestination(destination);
        return repository.save(newHotel);
    }
}