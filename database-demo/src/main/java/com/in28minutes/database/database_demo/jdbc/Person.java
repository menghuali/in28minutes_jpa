package com.in28minutes.database.database_demo.jdbc;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NamedQuery(name = "find_all_persons", query = "SELECT p FROM Person p")
@Builder
@Entity
// @Table(name = "person")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Person {

    @GeneratedValue
    @Id
    private int id;

    // @Column(name = "name")
    private String name;
    private String location;
    private Date birthdate;

}
