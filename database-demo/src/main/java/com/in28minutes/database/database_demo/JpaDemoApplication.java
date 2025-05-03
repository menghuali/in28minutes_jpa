package com.in28minutes.database.database_demo;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.in28minutes.database.database_demo.jdbc.Person;
import com.in28minutes.database.database_demo.jpa.PersonJpaRepo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class JpaDemoApplication implements CommandLineRunner {

	@Autowired
	private PersonJpaRepo repo;

	public static void main(String[] args) {
		SpringApplication.run(JpaDemoApplication.class, args);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void run(String... args) throws Exception {
		log.info("Persons -> {}", repo.findByAll());
		log.info("Persons named query -> {}", repo.findAll_NameQuery());
		log.info("Person id 1 -> {}", repo.findById(1));
		// log.info("Person name John Doe -> {}", repo.findByName("John Doe"));
		// log.info("Person location New York -> {}", repo.findByLocation("New York"));
		// log.info("Person birthdate 1990-01-01 -> {}", repo.findByBirthdate(LocalDate.of(1990, 2, 2)));
		repo.deleteById(10002);
		// log.info("Delete person name Jane Smith -> {}", repo.deleteByName("Jane Smith"));
		// log.info("Delete person location Chicago -> {}", repo.deleteByLocation("Chicago"));
		// log.info("Delete person birthdate 1975-04-04 -> {}", repo.deleteByBirthdate(LocalDate.of(1975, 4, 4)));
		log.info("Insert person -> {}",
				repo.insert(Person.builder().name("Bruce Wayne").location("Gotham City")
						.birthdate(new Date(80, 0, 0)).build()));
		log.info("Update person -> {}",
				repo.update(new Person(10001, "Bruce Wayne", "Gotham City", new Date(80, 0, 0))));
	}

}
