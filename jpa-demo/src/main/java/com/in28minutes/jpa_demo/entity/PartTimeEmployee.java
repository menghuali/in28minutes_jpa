package com.in28minutes.jpa_demo.entity;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@ToString
@Entity
public class PartTimeEmployee extends Employee {

    @Getter
    @Setter
    private BigDecimal hourlyWage;

    public PartTimeEmployee(String name, BigDecimal hourlyWage) {
        setName(name);
        this.hourlyWage = hourlyWage;
    }

}
