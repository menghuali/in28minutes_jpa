package com.in28minutes.jpa_demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.in28minutes.jpa_demo.entity.Course;

@RepositoryRestResource(path = "courses")
public interface CourseDataJpaRepo extends JpaRepository<Course, Long> {

    @EntityGraph(value = "Course.reviews", type = EntityGraphType.LOAD)
    List<Course> findByName(String name);

    Integer countByName(String name);

    List<Course> findByNameAndId(String name, Long id);

    List<Course> deleteByName(String name);

    @Query("SELECT c FROM Course c WHERE c.name LIKE '%a%'")
    List<Course> findCourseByJPQL();

    @Query(value = "SELECT * FROM Course WHERE name LIKE '%a%'", nativeQuery = true)
    List<Course> findCoursesByNativeQuery();

    @Query(name = "find_courses_with_a_in_name")
    List<Course> findCoursesByNamedQuery();
}
