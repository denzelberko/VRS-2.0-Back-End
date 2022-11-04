package com.example.vrs.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "reviews")
public class Review {

    @Id
    private Long reviewId;

    @ManyToOne
    @JoinColumn(name = "destinationsId")
    @NotEmpty
    @JsonIgnoreProperties({ "reviews" })
    private Destination destination;

    @NotEmpty
    private Long intuitiveScore;

    @NotEmpty
    private Long listScore;

    @NotEmpty
    private Long completenessScore;
}
