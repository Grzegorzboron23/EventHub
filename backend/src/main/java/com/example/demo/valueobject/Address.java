package com.example.demo.valueobject;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;

@Embeddable
@AllArgsConstructor
public class Address {

    @Size(min = 1, max = 100)
    private final String street;

    @Size(min = 1, max = 50)
    private final String city;

    @Pattern(regexp = "^[0-9]{2}-[0-9]{3}$")
    private final String postalCode;

    @Size(min = 1, max = 50)
    private final String country;
}
