package com.in28minutes.jpa_demo.repo;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.in28minutes.jpa_demo.entity.Course;
import com.in28minutes.jpa_demo.entity.Student;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
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

    @Test
    @Transactional
    public void courseWithoutStudent() {
        TypedQuery<Course> query = em.createQuery("SELECT c FROM Course c WHERE c.students is empty", Course.class);
        List<Course> courses = query.getResultList();
        log.info("Course without students -> {}", courses);
    }

    @Test
    @Transactional
    public void courseWithAleastTwoStudents() {
        TypedQuery<Course> query = em.createQuery("SELECT c FROM Course c WHERE size(c.students) >= 2", Course.class);
        List<Course> courses = query.getResultList();
        log.info("Course with at least two students -> {}", courses);
    }

    @Test
    @Transactional
    public void courseOrderByStudents() {
        TypedQuery<Course> query = em.createQuery("SELECT c FROM Course c ORDER BY size(c.students) DESC",
                Course.class);
        List<Course> courses = query.getResultList();
        log.info("Course with at least two students -> {}", courses);
    }

    @Test
    @Transactional
    public void studentsWithPassportInPattern() {
        TypedQuery<Student> query = em.createQuery("SELECT s FROM Student s WHERE s.passport.number LIKE '%3%2%'",
                Student.class);
        List<Student> students = query.getResultList();
        log.info("Students with passport like %3%2% -> {}", students);
    }

    @SuppressWarnings("unchecked")
    @Test
    @Transactional
    public void join() {
        Query query = em.createQuery("SELECT c, s FROM Course c JOIN c.students s");
        List<Object[]> result = query.getResultList();
        for (Object[] objects : result) {
            log.info("JOIN - Course: {}, Student: {}", objects[0], objects[1]);
        }
    }

    @SuppressWarnings("unchecked")
    @Test
    @Transactional
    public void leftJoin() {
        Query query = em.createQuery("SELECT c, s FROM Course c LEFT JOIN c.students s");
        List<Object[]> result = query.getResultList();
        for (Object[] objects : result) {
            log.info("LEFT JOIN - Course: {}, Student: {}", objects[0], objects[1]);
        }
    }

    @SuppressWarnings("unchecked")
    @Test
    @Transactional
    public void crossJoin() {
        Query query = em.createQuery("SELECT c, s FROM Course c, Student s");
        List<Object[]> result = query.getResultList();
        for (Object[] objects : result) {
            log.info("CROSS JOIN - Course: {}, Student: {}", objects[0], objects[1]);
        }
    }

}
