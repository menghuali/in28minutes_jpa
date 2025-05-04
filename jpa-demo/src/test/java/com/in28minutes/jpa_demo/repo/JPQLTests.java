package com.in28minutes.jpa_demo.repo;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.in28minutes.jpa_demo.entity.Course;

import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class JPQLTests {

    @Autowired
    private EntityManager em;

    @Test
    public void typedQuery() {
        List<Course> courses = em.createQuery("SELECT c FROM Course c", Course.class).getResultList();
        log.info("SELECT c FROM Course c -> {}", courses);
    }

    @Test
    public void typedQuery_Where() {
        List<Course> courses = em.createQuery("SELECT c FROM Course c WHERE name = ?1", Course.class)
                .setParameter(1, "Java")
                .getResultList();
        log.info("select c from Course c WHERE name = 'Java' -> {}", courses);
    }

    @Test
    public void namedQuery() {
        List<Course> courses = em.createNamedQuery("all_courses", Course.class).getResultList();
        log.info("Named query 'all_courses' -> {}", courses);
    }

    @Test
    public void namedQuery_WithParameters() {
        List<Course> courses = em.createNamedQuery("find_courses_by_name", Course.class).setParameter(1, "Java")
                .getResultList();
        log.info("Named query 'find_courses_by_name' -> {}", courses);
    }

}
