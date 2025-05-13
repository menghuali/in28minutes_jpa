package com.in28minutes.jpa_demo.repo;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import com.in28minutes.jpa_demo.entity.Course;

import lombok.extern.slf4j.Slf4j;

@Transactional
@SpringBootTest
@Slf4j
public class CourseDataJpaRepoTests {

    @Autowired
    private CourseDataJpaRepo repo;

    @Test
    public void findById() {
        Optional<Course> course = repo.findById(10001L);
        assertTrue(course.isPresent());
        log.info("Course 10001 -> {}", course.get());
    }

    @Test
    public void findById_notFound() {
        Optional<Course> course = repo.findById(20003L);
        assertTrue(course.isEmpty());
    }

    @Test
    public void sandbox() {
        Course course = new Course("Sandbox");
        course = repo.save(course);
        log.info("Course saved -> {}", repo.findById(course.getId()));
    }

    @Test
    public void sort() {
        log.info("Ascending sorted courses -> {}", repo.findAll(Sort.by("name").ascending()));
        log.info("Ascending sorted courses -> {}", repo.findAll(Sort.by("name").descending()));
    }

    @Test
    public void pagination() {
        log.info("First page of courses -> {}", repo.findAll(PageRequest.of(0, 2)).stream().toList());
        log.info("Second page of courses -> {}", repo.findAll(PageRequest.of(1, 2)).stream().toList());
    }

    @Test
    public void customQuery() {
        log.info("Find course by name -> {}", repo.findByName("Java"));
        log.info("Find course use JPQL -> {}", repo.findCourseByJPQL());
        log.info("Find course use Native Query -> {}", repo.findCoursesByNativeQuery());
        log.info("Find course use Named Query -> {}", repo.findCoursesByNamedQuery());
    }
}
