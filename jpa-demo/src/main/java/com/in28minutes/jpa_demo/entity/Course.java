package com.in28minutes.jpa_demo.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NamedQueries({
        @NamedQuery(name = "all_courses", query = "SELECT c FROM Course c"),
        @NamedQuery(name = "find_courses_by_name", query = "SELECT c from Course c WHERE name = ?1") })
@NoArgsConstructor
@Data
@Entity
public class Course {

    @Getter
    @GeneratedValue
    @Id
    private Long id;

    @Column(nullable = false)
    private String name;

    @CreationTimestamp
    private LocalDateTime createdTime;

    @UpdateTimestamp
    private LocalDateTime lastUpdatedTime;

    public Course(String name) {
        this.name = name;
    }

}
