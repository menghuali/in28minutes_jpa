package com.in28minutes.jpa_demo.repo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.in28minutes.jpa_demo.entity.Passport;

import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class PassportRepositoryTests {

    @Autowired
    private EntityManager em;

    @Test
    public void retrievePassportAndStudent() {
        Passport passport = em.find(Passport.class, 40001L);
        log.info("retrievePassportAndStudent - Passport -> {}", passport);
        log.info("retrievePassportAndStudent - Student -> {}", passport.getStudent());
    }

}
