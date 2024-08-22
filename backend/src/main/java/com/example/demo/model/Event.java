package com.example.demo.model;

import com.example.demo.valueobject.Address;
import com.example.demo.valueobject.DateRange;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="Event")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Event extends BaseEntity{

    @Embedded
    @NotNull
    private DateRange dateRange;

    @OneToOne
    @NotNull
    private User users;

    @NotNull
    private String name;

    @Embedded
    private Address address;
}
