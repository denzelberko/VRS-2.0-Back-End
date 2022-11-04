package com.example.vrs.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "attractions")
public class Attraction {
    @Id
    @NotEmpty
    private long id;

    @ManyToOne
    @JoinColumn(name = "destinationId")
    @NotEmpty
    @JsonBackReference
    private Destination destination;

    @NotEmpty
    private String name;

    @NotEmpty
    private float hoursToVisit;

    @NotEmpty
    private String type;

    @NotEmpty
    private String description;

    @NotEmpty
    private float cost;
}