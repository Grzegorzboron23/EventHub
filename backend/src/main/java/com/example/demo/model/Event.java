package com.example.demo.model;

import com.example.demo.dto.EventDTO;
import com.example.demo.repository.UserRepository;
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

    public Event(EventDTO eventDTO, User user) {
        this.name = eventDTO.getEventName();
        this.description = eventDTO.getEventDescription();
        this.setDateRange(
                new DateRange(
                        eventDTO.getStartTime(),
                        eventDTO.getEndTime()
                )
        );

        this.setAddress(
                new Address(
                            eventDTO.getAddress().getStreet(),
                        eventDTO.getAddress().getCity(),
                        eventDTO.getAddress().getPostalCode(),
                        eventDTO.getAddress().getCountry()
                )
        );

        this.setUsers(user);
    }
}
