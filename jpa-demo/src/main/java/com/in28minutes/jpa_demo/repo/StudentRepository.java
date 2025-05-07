package com.in28minutes.jpa_demo.repo;

import org.springframework.stereotype.Repository;

import com.in28minutes.jpa_demo.entity.Course;
import com.in28minutes.jpa_demo.entity.Passport;
import com.in28minutes.jpa_demo.entity.Student;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@AllArgsConstructor
@Repository
public class StudentRepository {

    private final EntityManager em;

    public Student findById(Long id) {
        return em.find(Student.class, id);
    }

    public void deleteById(Long id) {
        Student student = findById(id);
        if (student != null) {
            em.remove(student);
        }
    }

    public Student save(Student student) {
        if (student.getId() == null) {
            em.persist(student);
        } else {
            em.merge(student);
        }
        return student;
    }

    public Student createStudentWithPassport() {
        Passport passport = new Passport("K74329815");
        em.persist(passport);
        Student student = new Student("Wade Wilson", passport);
        em.persist(student);
        return student;
    }

    public Student updateStudent() {
        Student student = em.find(Student.class, 20002L);
        student.setName(student.getName() + " - Updated");
        return student;
    }

    public Student mulitpleOperations() {
        Passport passport = new Passport("K1234567");
        em.persist(passport);
        // Data is synchronized to the database immediately, but the transaction is not
        // committed at this point. The change is ONLY visible to the current
        // transaction.
        em.flush();
        Student student = new Student("James Howlett", passport);
        em.persist(student);
        em.flush();
        student.setName("Logan");
        em.merge(student);
        em.flush();
        return student;
    }

    public void insertStudentAndCourse(Student student, Course course) {
        student.addCourse(course);
        course.addStudent(student);
        em.persist(student);
        em.persist(course);
    }

}
