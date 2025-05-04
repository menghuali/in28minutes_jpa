package com.in28minutes.jpa_demo.repo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.in28minutes.jpa_demo.entity.Course;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class NativeQueryTests {

    @Autowired
    private EntityManager em;

    @Test
    public void nativeQuery() {
        log.info("Native query -> {}", em.createNativeQuery("SELECT * FROM COURSE").getResultList());
    }

    @Test
    public void nativeQuery_Mapped() {
        log.info("Native query mapped -> {}",
                em.createNativeQuery("SELECT * FROM COURSE", Course.class).getResultList());
    }

    @Test
    public void nativeQuery_WithParameter() {
        log.info("Native query with parameter -> {}",
                em.createNativeQuery("SELECT * FROM COURSE WHERE NAME = ?", Course.class).setParameter(1, "Java")
                        .getResultList());
    }

    @Test
    public void nativeQuery_WithNamedParameter() {
        log.info("Native query with named parameter -> {}",
                em.createNativeQuery("SELECT * FROM COURSE WHERE NAME = :name", Course.class)
                        .setParameter("name", "Java")
                        .getResultList());
    }

    @Transactional
    @Test
    public void nativeQuery_UpdateAllRows() {
        log.info("Native query update -> {}",
                em.createNativeQuery("UPDATE COURSE SET LAST_UPDATED_TIME = CURRENT_TIMESTAMP").executeUpdate());
    }

}
