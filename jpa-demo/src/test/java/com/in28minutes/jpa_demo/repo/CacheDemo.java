package com.in28minutes.jpa_demo.repo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.in28minutes.jpa_demo.entity.Course;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class CacheDemo {

    @Autowired
    private CourseRepository repo;

    @Transactional
    @Test
    public void cacheDemo_Transactional() {
        Course course = repo.findById(10001L);
        log.info("First Course (Transactional): {}", course.getName());

        Course course1 = repo.findById(10001L);
        log.info("Second Course (Transactiona;): {}", course1.getName());
    }

    @Test
    public void cacheDemo_NonTransactional() {
        Course course = repo.findById(10001L);
        log.info("First Course (Non Transactional): {}", course.getName());

        Course course1 = repo.findById(10001L);
        log.info("Second Course (Non Transactiona;): {}", course1.getName());
    }

}
