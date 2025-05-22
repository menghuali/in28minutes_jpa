package com.in28minutes.jpa_demo.repo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.in28minutes.jpa_demo.entity.Address;
import com.in28minutes.jpa_demo.entity.Passport;
import com.in28minutes.jpa_demo.entity.Student;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class StudentRepositoryTests {

    @Autowired
    private EntityManager em;

    @Transactional
    @Test
    public void retrieveStudentAndPassport() {
        Student student = em.find(Student.class, 20001L);
        student.setAddress(new Address("No 101", "Some Street", "Toronto"));
        em.flush();
        // This method need transaction because passport is lazy fetch.
        Passport passport = student.getPassport();
        log.info("retrieveStudentAndPassport - Student 10001L -> {}", student);
        log.info("retrieveStudentAndPassport - Passport -> {}", passport);
    }

    @Test
    @Transactional
    public void someTest() {
        Student student = em.find(Student.class, 20001L);
        Passport passport = student.getPassport();
        passport.setNumber(passport.getNumber() + "_A");

        student.setName(student.getName() + "_A");

        log.info("someTest - End");
    }

    /**
     * ManyToMany uses lazy fetch by default. So, @Transactional is needed.
     */
    @Test
    @Transactional
    public void testManyToMany() {
        Student student = em.find(Student.class, 20001L);
        log.info("Student -> {}", student);
    }

}
