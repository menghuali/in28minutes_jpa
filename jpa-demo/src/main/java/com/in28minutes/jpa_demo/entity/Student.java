package com.in28minutes.jpa_demo.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Data
@Entity
public class Student {

    @Setter(AccessLevel.PROTECTED)
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToOne(fetch = FetchType.LAZY)
    private Passport passport;

    @JoinTable(name = "student_course", joinColumns = { @JoinColumn(name = "student_id") }, inverseJoinColumns = {
            @JoinColumn(name = "course_id") })
    @ManyToMany
    @Setter(AccessLevel.PROTECTED)
    private List<Course> courses = new ArrayList<>();

    @Embedded // Embedded object
    private Address address;

    public Student(String name) {
        this.name = name;
    }

    public Student(String name, Passport passport) {
        this.name = name;
        this.passport = passport;
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public void removeCourse(Course course) {
        courses.remove(course);
    }

}
