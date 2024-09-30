package com.example.demo.dto;

import com.example.demo.model.Event;
import com.example.demo.valueobject.Address;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class EventDTO {

    public EventDTO(Event event) {
        this.eventId = event.getId();
        this.userId = event.getUsers().getId();
        this.eventName = event.getName();
        this.eventDescription = event.getDescription();
        this.startTime = event.getDateRange().startDate();
        this.endTime = event.getDateRange().endDate();
        this.address = event.getAddress();
    }

    private final Long eventId;
    private final Long userId;
    private final String eventName;
    private final String eventDescription;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
    private Address address;
}
