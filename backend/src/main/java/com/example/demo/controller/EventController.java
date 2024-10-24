package com.example.demo.controller;

import com.example.demo.dto.EventDTO;
import com.example.demo.service.EventService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.List;

@Controller
@CrossOrigin(origins = "http://localhost:3000")
@AllArgsConstructor
@RequestMapping("/event")
public class EventController {

    private final EventService eventService;

    @GetMapping("{id}")
    public ResponseEntity<EventDTO> getSingleEvent(@PathVariable Long id) {
        return ResponseEntity.ok(eventService.getSingleEvent(id));
    }

    @GetMapping("/all/{page}")
    public ResponseEntity<List<EventDTO>> getEventByPage(@PathVariable(required = false) Integer page) {
        return ResponseEntity.ok(eventService.getEvents(page == null ? 1 : page));
    }

    @PostMapping
    public ResponseEntity<EventDTO> createEvent(@RequestBody  EventDTO eventDTO) {
        return ResponseEntity.ok(eventService.createEvent(eventDTO));
    }

    @DeleteMapping("/{eventId}")
    public ResponseEntity<String> deleteEvent(@PathVariable  Long eventId) throws AccessDeniedException {
        eventService.deleteEvent(eventId);
        return ResponseEntity.ok("Event deleted succesfully");
    }

    @PutMapping("/{eventId}")
    public ResponseEntity<EventDTO> updateEvent(@RequestBody  EventDTO eventDTO) throws AccessDeniedException {
        return ResponseEntity.ok(eventService.updateEvent(eventDTO));
    }
}
