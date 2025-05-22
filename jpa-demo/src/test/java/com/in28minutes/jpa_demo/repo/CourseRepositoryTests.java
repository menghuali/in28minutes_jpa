package com.in28minutes.jpa_demo.repo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.DirtiesContext;

import com.in28minutes.jpa_demo.JpaDemoApplication;
import com.in28minutes.jpa_demo.entity.Course;
import com.in28minutes.jpa_demo.entity.Review;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest(classes = JpaDemoApplication.class)
public class CourseRepositoryTests {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private EntityManager em;

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
        courseRepository.deleteById(10004L);
        Course course = courseRepository.findById(10004L);
        assertNull(course);
    }

    @Test
    public void testNullable() {
        assertThrows(DataIntegrityViolationException.class, () -> courseRepository.save(new Course()));
    }

    /**
     * OneToMany by default is lazy fetch
     */
    @Transactional
    @Test
    public void getCourseReviews() {
        Course course = courseRepository.findById(10001L);
        log.info("Course views -> {}", course.getReviews());
    }

    /**
     * ManyToOne by default is earger fetch
     */
    @Test
    public void getReview() {
        Review review = em.find(Review.class, 50001L);
        log.info("Review -> {}", review);
    }
}
