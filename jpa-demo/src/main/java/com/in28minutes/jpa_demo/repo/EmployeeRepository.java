package com.in28minutes.jpa_demo.repo;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.in28minutes.jpa_demo.entity.Employee;
import com.in28minutes.jpa_demo.entity.FullTimeEmployee;
import com.in28minutes.jpa_demo.entity.PartTimeEmployee;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@Repository
@Transactional
public class EmployeeRepository {

    private final EntityManager em;

    public void insert(Employee employee) {
        em.persist(employee);
    }

    public List<Employee> findAll() {
        return em.createQuery("SELECT e FROM Employee e", Employee.class).getResultList();
    }

    public List<FullTimeEmployee> findAllFullTime() {
        return em.createQuery("SELECT e FROM FullTimeEmployee e", FullTimeEmployee.class).getResultList();
    }

    public List<PartTimeEmployee> findAllPartTime() {
        return em.createQuery("SELECT e FROM PartTimeEmployee e", PartTimeEmployee.class).getResultList();
    }
}
