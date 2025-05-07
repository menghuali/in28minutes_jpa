package com.in28minutes.jpa_demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@NoArgsConstructor
@Entity
public class Passport {

    @Setter(AccessLevel.PROTECTED)
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String number;

    @ToString.Exclude
    @OneToOne(mappedBy = "passport")
    private Student student;

    public Passport(String number) {
        this.number = number;
    }

}
