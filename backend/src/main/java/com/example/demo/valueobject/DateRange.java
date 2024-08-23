package com.example.demo.valueobject;

import jakarta.persistence.Embeddable;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Embeddable
public record DateRange(LocalDateTime startDate, LocalDateTime endDate) {

    public DateRange {
        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("Start date cannot be after end date");
        }

        if (isToday(startDate)) {
            throw new IllegalArgumentException("Start date cannot be today");
        }
    }

    public boolean isToday(LocalDateTime date) {
        return date.toLocalDate().equals(LocalDate.now());
    }
}
