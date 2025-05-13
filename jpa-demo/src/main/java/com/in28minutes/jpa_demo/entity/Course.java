package com.in28minutes.jpa_demo.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NamedQueries({
        @NamedQuery(name = "all_courses", query = "SELECT c FROM Course c"),
        @NamedQuery(name = "find_courses_by_name", query = "SELECT c from Course c WHERE name = ?1"),
        @NamedQuery(name = "find_courses_with_a_in_name", query = "SELECT c from Course c WHERE name LIKE '%a%'") })
@NoArgsConstructor
@Data
@Entity
public class Course {

    @Setter(AccessLevel.PROTECTED)
    @GeneratedValue
    @Id
    private Long id;

    @Column(nullable = false)
    private String name;

    @CreationTimestamp
    private LocalDateTime createdTime;

    @UpdateTimestamp
    private LocalDateTime lastUpdatedTime;

    @OneToMany(mappedBy = "course")
    @Setter(AccessLevel.PROTECTED)
    private List<Review> reviews = new ArrayList<>();

    @JsonIgnore
    @ManyToMany(mappedBy = "courses")
    @Setter(AccessLevel.PROTECTED)
    @ToString.Exclude
    private List<Student> students = new ArrayList<>();

    public Course(String name) {
        this.name = name;
    }

    public void addReview(Review review) {
        reviews.add(review);
    }

    public void removeReview(Review review) {
        reviews.remove(review);
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void removeStudent(Student student) {
        students.remove(student);
    }

}
