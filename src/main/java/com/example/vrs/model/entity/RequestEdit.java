package com.example.vrs.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "requestedits")
public class RequestEdit {

    @Id
    @NotEmpty
    private Long requestId;

    @ManyToOne
    @JoinColumn(name = "destinationsId")
    @NotEmpty
    @JsonIgnoreProperties({ "requestedits" })
    private Destination destination;

    @NotEmpty
    private String type;

    @NotEmpty
    private String description;

    @NotEmpty
    private String resolved;

}
