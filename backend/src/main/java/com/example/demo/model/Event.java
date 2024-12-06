package com.example.demo.model;

import com.example.demo.dto.EventDTO;
import com.example.demo.valueobject.Address;
import com.example.demo.valueobject.DateRange;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

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

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @NotNull
    private String name;

    private String description;

    //    TODO: Implement it save on local server
    private List<String> imagePath;

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

        this.setUser(user);
    }
}
