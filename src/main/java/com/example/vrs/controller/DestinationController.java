package com.example.vrs.controller;

import com.example.vrs.controller.dto.DestinationDto;
import com.example.vrs.controller.exceptions.DestinationNotFoundException;
import com.example.vrs.model.entity.Destination;
import com.example.vrs.model.repository.DestinationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
public class DestinationController {
    @Autowired
    private final DestinationRepository repository;

    public DestinationController(DestinationRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/destinations")
    List<Destination> retrieveAllDestinations() {
        return repository.findAll();
    }

    @GetMapping("/destinations/{id}")
    Destination retrieveDestination(@PathVariable("id") Long id) {
        return repository.findById(id).orElseThrow(
                () -> new DestinationNotFoundException(id));
    }

    @PostMapping("/destinations")
    Destination createDestination(@RequestBody DestinationDto destinationDto) {
        Destination newDestination = new Destination();
        newDestination.setId(destinationDto.getId());
        newDestination.setName(destinationDto.getName());
        newDestination.setWeather(destinationDto.getWeather());
        newDestination.setKidFriendlyScore(destinationDto.getKidFriendlyScore());
        newDestination.setFoodQualityScore(destinationDto.getFoodQualityScore());
        newDestination.setPriceIndex(destinationDto.getPriceIndex());
        newDestination.setInstagramAbilityScore(destinationDto.getInstagramAbilityScore());
        newDestination.setNativeLanguage(destinationDto.getNativeLanguage());
        newDestination.setPurpose(destinationDto.getPurpose());
        newDestination.setHotelQualityScore(destinationDto.getHotelQualityScore());
        newDestination.setCountry(destinationDto.getCountry());
        newDestination.setContinent(destinationDto.getContinent());
        newDestination.setCurrency(destinationDto.getCurrency());
        newDestination.setAttractionScore(destinationDto.getAttractionScore());
        newDestination.setSafetyScore(destinationDto.getSafetyScore());
        newDestination.setPopularity(destinationDto.getPopularity());
        return repository.save(newDestination);
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

    @DeleteMapping("/destinations/{id}")
    void deleteDestination(@PathVariable("id") Long id) {
        repository.deleteById(id);
    }

    @GetMapping("/destinations/{priceIndex}/{purpose}/{weather}")
    List<Destination> searchDestinations(@PathVariable("priceIndex") Long PriceIndex, @PathVariable("purpose") String purpose, @PathVariable("weather") String weather) {
        return repository.search(PriceIndex, purpose, weather);
    }
}
