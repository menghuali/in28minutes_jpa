package com.in28minutes.jpa_demo.repo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.DirtiesContext;

import com.in28minutes.jpa_demo.JpaDemoApplication;
import com.in28minutes.jpa_demo.entity.Course;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest(classes = JpaDemoApplication.class)
public class CourseRepositoryTests {

    @Autowired
    private CourseRepository courseRepository;

    @Test
    void findById() {
        Course course = courseRepository.findById(10001L);
        assertEquals("Java", course.getName());
    }

    @DirtiesContext
    @Test
    void save() {
        Course course = new Course("Python");
        courseRepository.save(course);
        log.info("Saved course: {}", course);
        assertEquals("Python", course.getName());
        assertNotNull(course.getId());
    }

    @DirtiesContext
    @Test
    void deleteById() {
        courseRepository.deleteById(10002L);
        assertEquals(null, courseRepository.findById(10002L));
    }

    @Test
    public void testNullable() {
        assertThrows(DataIntegrityViolationException.class, () -> courseRepository.save(new Course()));
    }
}
