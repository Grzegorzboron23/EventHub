package com.example.demo;

import com.example.demo.model.Event;
import com.example.demo.model.User;
import com.example.demo.repository.EventRepository;
import com.example.demo.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
@Transactional
public class EventRepositoryTest {
    @Autowired
    EventRepository eventRepository;

    @Autowired
    UserRepository userRepository;

    User user = TestUtils.createCorrectUser();
    Event event = TestUtils.createCorrectEvent();


    @BeforeEach
    public void initializeDatabase() {
        event.setUser(user);

        userRepository.save(user);
        eventRepository.save(event);
    }

    @Test
    public void testSaveEvent() {
        assertNotNull(event);
    }


    @Test
    public void testFindByNameContainingIgnoreCase() {
        Page<Event> events = eventRepository.findByNameContainingIgnoreCase(event.getName().substring(2), Pageable.ofSize(10));

        assertEquals(1, events.getTotalElements());
        assertEquals(event.getName(), events.getContent().get(0).getName());
    }

    @Test
    public void testFindByNameIgnoreCase() {
        Optional<Event> foundEvent = eventRepository.findByNameIgnoreCase(event.getName());

        assertTrue(foundEvent.isPresent());
        assertEquals(event.getName(), foundEvent.get().getName());
    }

    @Test
    public void testFindByAddress_City() {
        Page<Event> eventsInCity = eventRepository.findByAddress_City(event.getAddress().getCity(), Pageable.ofSize(10));

        assertFalse(eventsInCity.isEmpty());
        assertEquals(1, eventsInCity.getTotalElements());
        assertEquals(event.getAddress().getCity(), eventsInCity.getContent().get(0).getAddress().getCity());
    }

    @Test
    public void testFindByAddress_Country() {
        Page<Event> eventsInCountry = eventRepository.findByAddress_Country(event.getAddress().getCountry(), Pageable.ofSize(10));

        assertFalse(eventsInCountry.isEmpty());
        assertEquals(1, eventsInCountry.getTotalElements());
        assertEquals(event.getAddress().getCountry(), eventsInCountry.getContent().get(0).getAddress().getCountry());
    }

    @Test
    public void testFindByNameContainingIgnoreCaseNoResults() {
        Page<Event> events = eventRepository.findByNameContainingIgnoreCase("nonexistent", Pageable.ofSize(10));

        assertTrue(events.isEmpty());
    }

    @Test
    public void testFindByAddress_CityNoResults() {
        Page<Event> eventsInCity = eventRepository.findByAddress_City("Nonexistent City", Pageable.ofSize(10));

        assertTrue(eventsInCity.isEmpty());
    }
}
