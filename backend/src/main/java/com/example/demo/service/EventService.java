package com.example.demo.service;


import com.example.demo.dto.EventDTO;
import com.example.demo.model.Event;
import com.example.demo.model.User;
import com.example.demo.repository.EventRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.valueobject.DateRange;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EventService {

    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    int PAGE_SIZE = 30;

    public EventDTO getSingleEvent(Long id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Event not found with id: " + id));
        return new EventDTO(event);
    }

    public List<EventDTO> getEvents(Integer page) {
        PageRequest pageRequest = PageRequest.of(page - 1, PAGE_SIZE, Sort.by("startDate").descending());
        Page<Event> eventPage = eventRepository.findAll(pageRequest);

        return eventPage.stream()
                .map(EventDTO::new)
                .toList();
    }

//    TODO: Add posibility to delete event only for creator and admin
    @Transactional
    public void deleteEvent(Long id){
        Event event = eventRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Event not found please try again later"));
        eventRepository.delete(event);
    }

//    TODO getLoggedUser to create event
    @Transactional
    public Event addNewEvent(EventDTO eventDTO){
        User user = userRepository.findById(eventDTO.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("Cannot find user please try again later"));

        Event event = new Event();
        event.setName(eventDTO.getEventName());
        event.setDescription(eventDTO.getEventDescription());
        event.setDateRange(new DateRange(eventDTO.getStartTime(),eventDTO.getEndTime()));
        event.setAddress(eventDTO.getAddress());
        event.setUsers(user);

        return eventRepository.save(event);
    }


}
