package com.example.vrs.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.lang.Nullable;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "destinations")
public class Destination {

    @Id
    private long id;

    @OneToMany(mappedBy = "destination")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @Nullable
    @JsonManagedReference
    private List<Attraction> attractions = new ArrayList<>();

    @OneToMany(mappedBy = "destination")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @Nullable
    private List<Hotel> hotels = new ArrayList<>();

    @OneToMany(mappedBy = "destination")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @Nullable
    @JsonManagedReference
    private List<Review> reviews = new ArrayList<>();

    @NotEmpty
    private String name;

    @NotEmpty
    private String weather;

    @NotNull
    private Long kidFriendlyScore;

    @NotNull
    private Long foodQualityScore;

    @NotNull
    private Long priceIndex;

    @NotNull
    private Long instagramAbilityScore;

    @NotEmpty
    private String nativeLanguage;

    @NotEmpty
    private String purpose;

    @NotNull
    private Long hotelQualityScore;

    @NotNull
    private Long popularity; // changed from String

    @NotEmpty
    private String recTripLength;

    @NotEmpty
    private String country;

    @NotEmpty
    private String continent;

    @NotEmpty
    private String currency;

    @NotNull
    private Long attractionScore;

    @NotNull
    private Long safetyScore; // changed from String

    @Nullable
    private String imageURL;

    @Nullable
    private Double score;

    @Nullable
    private Integer rank;

}
