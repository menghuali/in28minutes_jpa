package com.in28minutes.database.database_demo;

import java.time.LocalDate;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.in28minutes.database.database_demo.jdbc.Person;
import com.in28minutes.database.database_demo.jdbc.PersonJdbcDao;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class DatabaseDemoApplication implements CommandLineRunner {

	@Autowired
	private PersonJdbcDao personJdbcDao;

	public static void main(String[] args) {
		SpringApplication.run(DatabaseDemoApplication.class, args);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void run(String... args) throws Exception {
		log.info("Persons -> {}", personJdbcDao.findByAll());
		log.info("Person id 1 -> {}", personJdbcDao.findById(1));
		log.info("Person name John Doe -> {}", personJdbcDao.findByName("John Doe"));
		log.info("Person location New York -> {}", personJdbcDao.findByLocation("New York"));
		log.info("Person birthdate 1990-01-01 -> {}", personJdbcDao.findByBirthdate(LocalDate.of(1990, 2, 2)));
		log.info("Delete person id 1 -> {}", personJdbcDao.deleteById(1));
		log.info("Delete person name Jane Smith -> {}", personJdbcDao.deleteByName("Jane Smith"));
		log.info("Delete person location Chicago -> {}", personJdbcDao.deleteByLocation("Chicago"));
		log.info("Delete person birthdate 1975-04-04 -> {}", personJdbcDao.deleteByBirthdate(LocalDate.of(1975, 4, 4)));
		log.info("Insert person -> {}",
				personJdbcDao.insert(new Person(6, "Peter Parker", "New York", new Date(80, 0, 0))));
		log.info("Update person -> {}",
				personJdbcDao.update(new Person(6, "Bruce Wayne", "Gotham City", new Date(80, 0, 0))));
	}

}
