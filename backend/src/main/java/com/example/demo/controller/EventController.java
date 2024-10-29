package com.example.demo.controller;

import com.example.demo.dto.EventDTO;
import com.example.demo.service.EventService;
import com.example.demo.utils.PagedResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;

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
    public ResponseEntity<PagedResponse<EventDTO>> getEventByPage(@PathVariable(required = false) Integer page) {
        int pageNumber = (page != null && page > 0) ? page : 1;
        return ResponseEntity.ok(eventService.getEvents(pageNumber));
    }

    @GetMapping({"/eventsByName/{name}", "/eventsByName/{name}/{page}"})
    public ResponseEntity<PagedResponse<EventDTO>> getEventsByName(
            @PathVariable String name,
            @PathVariable(required = false) Integer page) {
        int pageNumber = (page != null && page > 0) ? page : 1;
        return ResponseEntity.ok(eventService.findByNameContaining(name, pageNumber));
    }

    @PostMapping
    public ResponseEntity<EventDTO> createEvent(@RequestBody  EventDTO eventDTO) {
        return ResponseEntity.ok(eventService.createEvent(eventDTO));
    }

    @DeleteMapping("/{eventId}")
    public ResponseEntity<String> deleteEvent(@PathVariable  Long eventId) throws AccessDeniedException {
        eventService.deleteEvent(eventId);
        return ResponseEntity.ok("Event deleted successfully");
    }

    @PutMapping("/{eventId}")
    public ResponseEntity<EventDTO> updateEvent(@RequestBody  EventDTO eventDTO) throws AccessDeniedException {
        return ResponseEntity.ok(eventService.updateEvent(eventDTO));
    }
}
