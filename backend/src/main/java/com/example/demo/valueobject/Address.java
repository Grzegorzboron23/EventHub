package com.example.demo.valueobject;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Address {

    @Size(min = 1, max = 100)
    private String street;

    @Size(min = 1, max = 50)
    private String city;

    @Pattern(regexp = "^[0-9]{2}-[0-9]{3}$")
    private String postalCode;

    @Size(min = 1, max = 50)
    private String country;
}
