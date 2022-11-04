package com.example.vrs.controller;

import com.example.vrs.model.entity.RequestEdit;
import com.example.vrs.model.repository.RequestEditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class RequestEditController {
    @Autowired
    private final RequestEditRepository repository;

    public RequestEditController(RequestEditRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/requestedits")
    List<RequestEdit> retrieveAllRequestEdits() {
        return repository.findAll();
    }

    @PostMapping("/requestedits")
    RequestEdit createRequestEdit(@RequestBody RequestEdit requestEdit) {
        return repository.save(requestEdit);
    }

}
