package com.in28minutes.jpa_demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
public class Review {

    @Setter(AccessLevel.PROTECTED)
    @Id
    @GeneratedValue
    private Long id;

    private String description;

    @Enumerated(EnumType.STRING)
    private Rating rating;

    @ToString.Exclude
    @ManyToOne
    private Course course;

    public Review(String description, Rating rating) {
        this.description = description;
        this.rating = rating;
    }

    public Review(String description, Rating rating, Course course) {
        this.description = description;
        this.rating = rating;
        this.course = course;
    }

}
