package com.example.demo.valueobject;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.Objects;

@Embeddable
public class Address {

    @Size(min = 1, max = 100)
    private final String street;

    @Size(min = 1, max = 50)
    private final String city;

    @Pattern(regexp = "^[0-9]{2}-[0-9]{3}$")
    private final String postalCode;

    @Size(min = 1, max = 50)
    private final String country;

    public Address(String street, String city, String postalCode, String country) {
        this.street = Objects.requireNonNull(street, "Street cannot be null");
        this.city = Objects.requireNonNull(city, "City cannot be null");
        this.postalCode = Objects.requireNonNull(postalCode, "Postal code cannot be null");
        this.country = Objects.requireNonNull(country, "Country cannot be null");
    }
}
