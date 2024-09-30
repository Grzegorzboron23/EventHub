package com.example.demo.controller;

import com.example.demo.dto.EventDTO;
import com.example.demo.service.EventService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@AllArgsConstructor
public class EventController {

    private final EventService eventService;

    @GetMapping("/event/{id}")
    public ResponseEntity<EventDTO> getSingleEvent(@PathVariable Long id) {
        return ResponseEntity.ok(eventService.getSingleEvent(id));
    }

    @GetMapping("/event/all/{page}")
    public ResponseEntity<List<EventDTO>> getEventByPage(@PathVariable(required = false) Integer page) {
        return ResponseEntity.ok(eventService.getEvents(page == null ? 1 : page));
    }

}
