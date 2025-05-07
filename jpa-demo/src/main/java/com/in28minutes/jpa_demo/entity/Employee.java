package com.in28minutes.jpa_demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.MappedSuperclass;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@Entity
// @MappedSuperclass
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Employee {

    @Setter(AccessLevel.PROTECTED)
    @GeneratedValue
    @Id
    private Long id;

    private String name;

}
