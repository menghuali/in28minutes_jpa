package com.in28minutes.database.database_demo.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

@SuppressWarnings("deprecation")
@Repository
public class PersonJdbcDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Person> findByAll() {
        return jdbcTemplate.query("SELECT * FROM person", new BeanPropertyRowMapper<Person>(Person.class));
    }

    public Person findById(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM person WHERE id = ?", new Object[] { id },
                new RowMapper<Person>() {

                    @SuppressWarnings("null")
                    @Override
                    @Nullable
                    public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
                        int id = rs.getInt("id");
                        String name = rs.getString("name");
                        String location = rs.getString("location");
                        Date birthdate = rs.getDate("birthdate");
                        return new Person(id, name, location, birthdate);
                    }

                });
    }

    public List<Person> findByName(String name) {
        return jdbcTemplate.query("SELECT * FROM person WHERE name = ?", new Object[] { name },
                new BeanPropertyRowMapper<Person>(Person.class));
    }

    public List<Person> findByLocation(String location) {
        return jdbcTemplate.query("SELECT * FROM person WHERE location = ?", new Object[] { location },
                new BeanPropertyRowMapper<Person>(Person.class));
    }

    public List<Person> findByBirthdate(LocalDate birthdate) {
        return jdbcTemplate.query("SELECT * FROM person WHERE birthdate = ?", new Object[] { birthdate },
                new BeanPropertyRowMapper<Person>(Person.class));
    }

    public int deleteById(int id) {
        return jdbcTemplate.update("DELETE FROM person WHERE id = ?", new Object[] { id });
    }

    public int deleteByName(String name) {
        return jdbcTemplate.update("DELETE FROM person WHERE name = ?", new Object[] { name });
    }

    public int deleteByLocation(String location) {
        return jdbcTemplate.update("DELETE FROM person WHERE location = ?", new Object[] { location });
    }

    public int deleteByBirthdate(LocalDate birthdate) {
        return jdbcTemplate.update("DELETE FROM person WHERE birthdate = ?", new Object[] { birthdate });
    }

    public int insert(Person person) {
        return jdbcTemplate.update("INSERT INTO person (id, name, location, birthdate) VALUES (?, ?, ?, ?)",
                new Object[] { person.getId(), person.getName(), person.getLocation(), person.getBirthdate() });
    }

    public int update(Person person) {
        return jdbcTemplate.update("UPDATE person SET name = ?, location = ?, birthdate = ? WHERE id = ?",
                new Object[] { person.getName(), person.getLocation(), person.getBirthdate(), person.getId() });
    }

}
