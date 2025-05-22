package com.in28minutes.jpa_demo.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Embeddable // Embedded object
public class Address {

    private String line1;
    private String line2;
    private String city;

}
