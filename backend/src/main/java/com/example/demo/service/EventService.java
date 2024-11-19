package com.example.demo.service;


import com.example.demo.configuration.SessionManager;
import com.example.demo.dto.EventDTO;
import com.example.demo.model.Event;
import com.example.demo.model.User;
import com.example.demo.repository.EventRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.utils.PagedResponse;
import com.example.demo.utils.UserUtils;
import com.example.demo.valueobject.DateRange;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class EventService {

    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    private final SessionManager sessionManager;
    int PAGE_SIZE = 30;

    public EventService(EventRepository eventRepository,
                        UserRepository userRepository,
                        SessionManager sessionManager) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
        this.sessionManager = sessionManager;
    }

    public EventDTO getSingleEvent(Long id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Event not found with id: " + id));
        return new EventDTO(event);
    }

    public PagedResponse<EventDTO> getEvents(Integer page) {
        PageRequest pageRequest = PageRequest.of(page - 1, PAGE_SIZE, Sort.by("dateRange.startDate").descending());
        Page<Event> eventPage = eventRepository.findAll(pageRequest);


        List<EventDTO> eventDTOs = eventPage.stream()
                .map(EventDTO::new)
                .toList();

        return new PagedResponse<>(
                eventDTOs,
                eventPage.getNumber(),
                eventPage.getSize(),
                eventPage.getTotalElements(),
                eventPage.getTotalPages(),
                eventPage.isLast()
        );
    }


    @Transactional
    public Event addNewEvent(EventDTO eventDTO) {
        User user = userRepository.findById(eventDTO.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("Cannot find user please try again later"));

        Event event = new Event();
        event.setName(eventDTO.getEventName());
        event.setDescription(eventDTO.getEventDescription());
        event.setDateRange(new DateRange(eventDTO.getStartTime(), eventDTO.getEndTime()));
        event.setAddress(eventDTO.getAddress());
        event.setUsers(user);

        return eventRepository.save(event);
    }

//    TODO: ADD TESTS
    @Transactional
    public EventDTO updateEvent(EventDTO eventDTO) throws AccessDeniedException {
        Event event = getEventIfUserHasAccess(eventDTO);

        event.setName(eventDTO.getEventName());
        event.setDescription(eventDTO.getEventDescription());
        event.setDateRange(new DateRange(eventDTO.getStartTime(), eventDTO.getEndTime()));
        event.setAddress(eventDTO.getAddress());
        event.setUsers(userRepository.findById(eventDTO.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("Cannot find user please try again later")));

        return new  EventDTO(eventRepository.save(event));
    }

    @Transactional
    public void deleteEvent(Long id) throws AccessDeniedException {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Event not found, please try again later"));
        User user = userRepository.findById(event.getUsers().getId())
                .orElseThrow(() -> new IllegalArgumentException("Cannot find user, please try again later"));

        Long eventCreatorId = event.getUsers().getId();
        if (!checkUserAccess(user, eventCreatorId)) {
            throw new AccessDeniedException("You cannot modify this event");
        }

        eventRepository.delete(event);
    }

    private Event getEventIfUserHasAccess(EventDTO eventDTO) throws AccessDeniedException {
        Event event = eventRepository.findById(eventDTO.getEventId())
                .orElseThrow(() -> new EntityNotFoundException("Event not found, please try again later"));
        User user = userRepository.findById(eventDTO.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("Cannot find user, please try again later"));

        Long eventCreatorId = event.getUsers().getId();
        if (!checkUserAccess(user, eventCreatorId)) {
            throw new AccessDeniedException("You cannot modify this event");
        }

        return event;
    }

    private boolean checkUserAccess(User user, Long eventCreatorId) {
        return UserUtils.isAdmin(user) || Objects.equals(sessionManager.getLoggedUserId(), eventCreatorId);
    }

//    TODO: ADD tests
    @Transactional
    public EventDTO createEvent(EventDTO eventDTO) {
        User user = userRepository.findById(eventDTO.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("Cannot find user, please try again later"));
        Event event = new Event(eventDTO, user);
       return new EventDTO(eventRepository.save(event));
    }


    public PagedResponse<EventDTO> findByNameContaining(String name, Integer page) {
        PageRequest pageRequest = PageRequest.of(page -1, PAGE_SIZE);
        Page<Event> events = eventRepository.findByNameContainingIgnoreCase(name, pageRequest);
        
        List<EventDTO> eventDTOs = events.stream()
                .map(EventDTO::new)
                .collect(Collectors.toList());

        return new PagedResponse<>(
                eventDTOs,
                events.getNumber(),
                events.getSize(),
                events.getTotalElements(),
                events.getTotalPages(),
                events.isLast()
        );
    }
}
