package com.example.vrs.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.lang.Nullable;

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
    @NotNull
    private long id;

    @ManyToOne
    @JoinColumn(name = "destinationId")
    @NotNull
    @JsonBackReference
    private Destination destination;

    @NotEmpty
    private String name;

    @NotNull
    private float hoursToVisit;

    @NotEmpty
    private String type;

    @NotEmpty
    private String description;

    @NotNull
    private float cost;

    @Nullable
    private String imageURL;
}