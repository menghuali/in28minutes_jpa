package com.in28minutes.database.database_demo.jdbc;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Person {

    private int id;
    private String name;
    private String location;
    private Date birthdate;

}
