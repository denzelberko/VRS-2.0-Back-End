package com.example.vrs.controller.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AttractionDto {
    private long id;
    private Long destinationId;
    private String name;
    private float hoursToVisit;
    private String type;
    private String description;
    private float cost;
    private String imageURL;
}
