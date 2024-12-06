package com.example.demo;

import com.example.demo.enums.RoleEnum;
import com.example.demo.model.Event;
import com.example.demo.model.User;
import com.example.demo.valueobject.Address;
import com.example.demo.valueobject.DateRange;
import com.example.demo.valueobject.UserPrivileges;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

public class TestUtils {
    public static Event createCorrectEvent() {
        return new Event(
                createCorrectDateRange(),
                new User(),
                "Event 1",
                "This is a test event",
                List.of("dddhfufhuouidfdh"),
                createCorrectAddress());
    }

    public static DateRange createCorrectDateRange() {
        return new DateRange(
                LocalDateTime.of(LocalDate.now().getYear() + 1, Month.APRIL, 12, 12, 20),
                LocalDateTime.of(LocalDate.now().getYear() + 2, Month.APRIL, 12, 12, 20)
        );
    }

    public static Address createCorrectAddress() {
        return new Address("Wyszynskiego", "Warsaw", "51-531", "Poland");
    }

    public static User createCorrectUser() {
        return new User(
                "Grzegorz",
                "Pietraszko",
                "GrzegorzP24",
                "dihdygfdfdyofdfdsy",
                List.of(new Event()),
                "grzegorzp24@gmail.com",
                "856738444",
                TestUtils.setAdminPrivileges(),
                0);
    }

    public static UserPrivileges setAdminPrivileges() {
        return new UserPrivileges(RoleEnum.ADMIN);
    }

}
