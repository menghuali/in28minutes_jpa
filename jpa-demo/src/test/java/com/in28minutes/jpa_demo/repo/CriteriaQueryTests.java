package com.in28minutes.jpa_demo.repo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.in28minutes.jpa_demo.entity.Course;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Transactional
@Slf4j
@SpringBootTest
public class CriteriaQueryTests {

    @Autowired
    private EntityManager em;

    @Test
    public void allCourses() {
        // 1. Define a query
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Course> cQuery = builder.createQuery(Course.class);

        // 2. Define table(s), aka from clause
        Root<Course> root = cQuery.from(Course.class);

        // 3. Build typed query from criteria query
        TypedQuery<Course> tQuery = em.createQuery(cQuery.select(root));
        log.info("All courses -> {}", tQuery.getResultList());
    }

    @Test
    public void coursesWithCertianName() {
        // 1. Define a query
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Course> cQuery = builder.createQuery(Course.class);

        // 2. Define table(s), aka from clause
        Root<Course> root = cQuery.from(Course.class);

        // 3. Define where clause
        Predicate predicate = builder.like(root.get("name"), "S%");
        cQuery.where(predicate);

        // 4. Build typed query from criteria query
        TypedQuery<Course> tQuery = em.createQuery(cQuery.select(root));
        List<Course> courses = tQuery.getResultList();
        log.info("Courses with certain name pattern -> {}", courses);
    }

    @Test
    public void coursesWithoutStudents() {
        // 1. Define a query
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Course> cQuery = builder.createQuery(Course.class);

        // 2. Define table(s), aka from clause
        Root<Course> root = cQuery.from(Course.class);

        // 3. Define where clause
        Predicate predicate = builder.isEmpty(root.get("students"));
        cQuery.where(predicate);

        // 4. Build typed query from criteria query
        TypedQuery<Course> tQuery = em.createQuery(cQuery.select(root));
        List<Course> courses = tQuery.getResultList();
        log.info("Courses without students -> {}", courses);
    }

    @Test
    public void join() {
        // 1. Define a query
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Course> cQuery = builder.createQuery(Course.class);

        // 2. Define table(s), aka from clause
        Root<Course> root = cQuery.from(Course.class);

        // 3. Join
        root.join("students");

        // 4. Build typed query from criteria query
        TypedQuery<Course> tQuery = em.createQuery(cQuery.select(root));
        List<Course> courses = tQuery.getResultList();
        log.info("Courses join students -> {}", courses);
    }

    @Test
    public void leftJoin() {
        // 1. Define a query
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Course> cQuery = builder.createQuery(Course.class);

        // 2. Define table(s), aka from clause
        Root<Course> root = cQuery.from(Course.class);

        // 3. Join
        root.join("students", JoinType.LEFT);

        // 4. Build typed query from criteria query
        TypedQuery<Course> tQuery = em.createQuery(cQuery.select(root));
        List<Course> courses = tQuery.getResultList();
        log.info("Courses join students -> {}", courses);
    }

}
