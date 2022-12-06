package com.example.vrs.controller.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DestinationDto {
    private long id;
    private String name;
    private String weather;
    private Long kidFriendlyScore;
    private Long foodQualityScore;
    private Long priceIndex;
    private Long instagramAbilityScore;
    private String nativeLanguage;
    private String purpose;
    private String recTripLength;
    private Long hotelQualityScore;
    private Long popularity;

    private String country;
    private String continent;
    private String currency;
    private Long attractionScore;
    private Long safetyScore;

    private String imageURL;

}
