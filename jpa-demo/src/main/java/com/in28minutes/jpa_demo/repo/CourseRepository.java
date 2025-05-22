package com.in28minutes.jpa_demo.repo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.in28minutes.jpa_demo.entity.Course;
import com.in28minutes.jpa_demo.entity.Rating;
import com.in28minutes.jpa_demo.entity.Review;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

/**
 * Repository for Course entity
 * This class handles CRUD operations for Course entity
 */
@Slf4j
@Transactional
@Repository
public class CourseRepository {

    @Autowired
    private EntityManager em;

    /**
     * Find course by id
     * 
     * @param id ID of the course
     * @return Course object
     */
    public Course findById(Long id) {
        return em.find(Course.class, id);
    }

    /**
     * Save course
     * 
     * @param course Course object to save
     * @return Saved Course object
     */
    public Course save(Course course) {
        if (course.getId() == null) {
            em.persist(course);
        } else {
            em.merge(course);
        }
        return course;
    }

    /**
     * Delete course by id
     * 
     * @param id ID of the course to delete
     */
    public void deleteById(Long id) {
        Course course = findById(id);
        em.remove(course);
    }

    public void playWithEntityManager() throws InterruptedException {
        log.info("playWithEntityManager - start");

        Course course = new Course("JavaScript");
        em.persist(course);
        Course course2 = new Course("XML");
        em.persist(course2);
        em.flush();

        // Detach all entities
        // em.clear();

        // This update will be persisted because the entire method is in one transaction
        course.setName("React");
        // Detach entity so that changes on it won't be persisted.
        // em.detach(course2);
        course2.setName("XSLT");

        // The the entity change will be lost. Its state will be refereshed from db
        em.refresh(course);

        em.flush();

        log.info("playWithEntityManager - end");
    }

    public void addReviews() {
        Course course = em.find(Course.class, 10002L);
        log.info("Course 10001 reviews -> {}", course.getReviews());
        Review review1 = new Review("Great course! Clear explanations, hands-on projects, very beginner-friendly.",
                Rating.FIVE,
                course);
        Review review2 = new Review("Good overview, but lacks real-world depth and advanced examples.", Rating.THREE,
                course);
        course.addReview(review1);
        course.addReview(review2);
        em.persist(review1);
        em.persist(review2);
    }

    public void addReview(Long courseId, List<Review> reviews) {
        Course course = em.find(Course.class, courseId);
        log.info("Course {} reviews -> {}", courseId, course.getReviews());
        for (Review review : reviews) {
            review.setCourse(course);
            course.addReview(review);
            em.persist(review);
        }
    }

}
