package com.in28minutes.jpa_demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
public class Course {

    @Getter
    @GeneratedValue
    @Id
    private Long id;
    
    private String name;

    public Course(String name) {
        this.name = name;
    }

}
