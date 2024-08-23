package com.example.demo.controller;

import com.example.demo.dto.EventDTO;
import com.example.demo.dto.EventFilterRequestDTO;
import com.example.demo.service.EventService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor
public class EventController {

    private final EventService eventService;

    @GetMapping("/event/{id}")
    public EventDTO getSingleEvent(@RequestParam Long id) {
        return eventService.getSingleEvent(id);
    }

    @GetMapping("/event/all/{page}")
    public List<EventDTO> getEventByPage(@RequestParam(required = false) Integer page) {
        List<EventDTO> eventDTOList = eventService.getEvents(page == null ? 1 : page);

        return eventDTOList;
    }

    @PostMapping("/event/all/{page}")
    public List<EventDTO> getEventsBetween(@RequestBody EventFilterRequestDTO filterRequest) {
        return eventService.getEventsBetweenDates(
                filterRequest.startDate(),
                filterRequest.endDate(),
                filterRequest.page()
        );

    }
}
