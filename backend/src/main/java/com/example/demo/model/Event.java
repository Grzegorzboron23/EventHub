package com.example.demo.model;

import com.example.demo.valueobject.Address;
import com.example.demo.valueobject.DateRange;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "Event")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Event extends BaseEntity {

    @Embedded
    @NotNull
    private DateRange dateRange;

    @OneToOne
    @NotNull
    private User users;

    @NotNull
    private String name;

    private String description;

    @Embedded
    private Address address;

}
