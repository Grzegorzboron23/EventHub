package com.example.demo.service;


import com.example.demo.dto.EventDTO;
import com.example.demo.model.Event;
import com.example.demo.repository.EventRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventService {

    private final EventRepository eventRepository;
    int PAGE_SIZE = 30;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }


    public EventDTO getSingleEvent(Long id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Event not found with id: " + id));
        return new EventDTO(event);
    }

    public List<EventDTO> getEvents(Integer page) {
        PageRequest pageRequest = PageRequest.of(page - 1, PAGE_SIZE, Sort.by("startDate").descending());
        Page<Event> eventPage = eventRepository.findAll(pageRequest);

        List<EventDTO> eventDTOList = eventPage.stream()
                .map(EventDTO::new)
                .toList();
        return eventDTOList;
    }

    public List<EventDTO> getEventsBetweenDates(LocalDateTime startDate, LocalDateTime endDate, int page) {
        Pageable pageable = PageRequest.of(page - 1, PAGE_SIZE, Sort.by("startDate").descending());
        List<Event> eventPage = eventRepository.getAllBetweenDates(startDate, endDate, pageable);

        return eventPage.stream()
                .map(EventDTO::new)
                .collect(Collectors.toList());
    }

}
