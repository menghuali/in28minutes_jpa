package com.in28minutes.jpa_demo.entity;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Entity
@ToString
public class FullTimeEmployee extends Employee {

    @Getter
    @Setter
    private BigDecimal salary;

    public FullTimeEmployee(String name, BigDecimal salary) {
        setName(name);
        this.salary = salary;
    }

}
