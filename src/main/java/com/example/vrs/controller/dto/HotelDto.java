package com.example.vrs.controller.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HotelDto {
    private long id;
    private Long destinationId;
    private String name;
    private float cost;
    private float rating;
}
