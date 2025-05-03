package com.in28minutes.database.database_demo.jpa;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.in28minutes.database.database_demo.jdbc.Person;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Transactional
@Repository
public class PersonJpaRepo {

    @PersistenceContext
    private EntityManager entityManager;

    public Person findById(int id) {
        return entityManager.find(Person.class, id);
    }

    public Person update(Person person) {
        return entityManager.merge(person);
    }

    public Person insert(Person person) {
        return entityManager.merge(person);
    }

    public void deleteById(int id) {
        Person person = findById(id);
        entityManager.remove(person);
    }

    public List<Person> findByAll() {
        return entityManager.createQuery("SELECT p FROM Person p", Person.class).getResultList();
    }

    public List<Person> findAll_NameQuery() {
        return entityManager.createNamedQuery("find_all_persons", Person.class).getResultList();
    }

}
