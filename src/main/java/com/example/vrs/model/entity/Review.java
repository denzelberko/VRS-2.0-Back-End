package com.example.vrs.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
    @NotNull
    @JsonBackReference
    private Destination destination;

    @NotNull
    private Long rating;

    @NotEmpty
    private String message;

}
