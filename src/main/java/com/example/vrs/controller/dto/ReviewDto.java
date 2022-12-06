package com.example.vrs.controller.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewDto {
    private Long reviewId;
    private Long destinationId;
    private Long rating;
    private String message;
}
