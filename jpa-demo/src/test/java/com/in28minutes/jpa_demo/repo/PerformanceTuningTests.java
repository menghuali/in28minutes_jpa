package com.in28minutes.jpa_demo.repo;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.in28minutes.jpa_demo.entity.Course;

import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class PerformanceTuningTests {

    @Autowired
    private EntityManager em;

    @Autowired private CourseDataJpaRepo jpaRepo;

    @Transactional
    @Test
    public void nPlusOneProblem() {
        List<Course> courses = em.createQuery("Select c from Course c", Course.class).getResultList();
        log.info("Courses -> {}", courses);
    }

    @Transactional
    @Test
    public void resovlingNPlusOneProblem_EntityGraph() {
        // Using EntityGraph to fetch all the data in one go
        EntityGraph<Course> entityGraph = em.createEntityGraph(Course.class);
        entityGraph.addSubgraph("reviews");

        List<Course> courses = em.createQuery("Select c from Course c", Course.class)
                .setHint("javax.persistence.loadgraph", entityGraph).getResultList();
        log.info("Courses -> {}", courses);
    }

    @Transactional
    @Test
    public void resolvingNPlusOneProblem_JPA() {
        List<Course> courses = jpaRepo.findByName("Java");
        log.info("Courses -> {}", courses);
    }

}
