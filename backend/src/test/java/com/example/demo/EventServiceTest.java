package com.example.demo;

import com.example.demo.configuration.SessionManager;
import com.example.demo.dto.EventDTO;
import com.example.demo.enums.RoleEnum;
import com.example.demo.model.Event;
import com.example.demo.model.User;
import com.example.demo.repository.EventRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.EventService;
import com.example.demo.utils.UserUtils;
import com.example.demo.valueobject.UserPrivileges;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class EventServiceTest {

    private EventService eventService;
    private final EventRepository eventRepository = mock(EventRepository.class);
    private final UserRepository userRepository = mock(UserRepository.class);
    private final SessionManager sessionManager = mock(SessionManager.class);



    @BeforeEach
    public void setUp() {
        eventService = new EventService(eventRepository, userRepository, sessionManager);
    }


    @Test
    public void whenEventExists_thenReturnEventDTO() {
        Event event = TestUtils.createCorrectEvent();
        when(eventRepository.findById(1L)).thenReturn(Optional.of(event));

        EventDTO result = eventService.getSingleEvent(1L);


        assertNotNull(result);
        assertEquals("Event 1", result.getEventName());
        verify(eventRepository, times(1)).findById(1L);
    }

    @Test
    public void whenEventNotExists_thenThrowEntityNotFoundException() {
        when(eventRepository.findById(1L)).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
                () -> eventService.getSingleEvent(1L)
        );

        assertEquals("Event not found with id: 1", exception.getMessage());
        verify(eventRepository, times(1)).findById(1L);
    }

    @Test
    public void whenGetEvents_thenReturnPagedEventDTOs() {
        List<Event> events = List.of(TestUtils.createCorrectEvent());

        Page<Event> eventPage = new PageImpl<>(events);
        when(eventRepository.findAll(any(PageRequest.class))).thenReturn(eventPage);

        List<EventDTO> eventDTOs = eventService.getEvents(1);

        assertNotNull(eventDTOs);
        assertEquals(1, eventDTOs.size());
        assertEquals("Event 1", eventDTOs.get(0).getEventName());

        verify(eventRepository, times(1)).findAll(any(Pageable.class));
    }


    @Test
    public void whenAddNewEvent_thenSave() {
        User user = TestUtils.createCorrectUser();

        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        Event event = TestUtils.createCorrectEvent();
        event.setUsers(user);

        EventDTO eventDTO = new EventDTO(event);
        when(eventRepository.save(any(Event.class))).thenReturn(event);

        Event savedEvent = eventService.addNewEvent(eventDTO);
        verify(eventRepository, times(1)).save(any(Event.class));
        assertNotNull(savedEvent);
        assertEquals("Event 1", savedEvent.getName());
        assertEquals("This is a test event", savedEvent.getDescription());
        assertEquals(user, savedEvent.getUsers());
    }

    @Test
    public void whenDeleteEventByAdmin_thenDelete(){
        Event event = TestUtils.createCorrectEvent();
        User user = TestUtils.createCorrectUser();

        event.setId(1L);
        event.setUsers(user);

        when(eventRepository.findById(event.getId())).thenReturn(Optional.of(event));
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));

        assertDoesNotThrow(() -> eventService.deleteEvent(event.getId()));
        verify(eventRepository, times(1)).delete(event);
    }

    @Test
    public void whenDeleteEventByOtherUser_thenThrowAccessDeniedException(){
        Event event = TestUtils.createCorrectEvent();
        User user = TestUtils.createCorrectUser();

        event.setId(1L);
        user.setUserPrivileges(new UserPrivileges(RoleEnum.DEFAULT));

        when(eventRepository.findById(event.getId())).thenReturn(Optional.of(event));
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));

        assertThrows(AccessDeniedException.class, () -> eventService.deleteEvent(event.getId()));

        verify(eventRepository, never()).delete(event);
    }

    @Test
    public void whenDeleteEventByCreator_thenDelete(){
        Event event = TestUtils.createCorrectEvent();
        User user = TestUtils.createCorrectUser();

        event.setId(1L);
        user.setUserPrivileges(new UserPrivileges(RoleEnum.DEFAULT));

        when(eventRepository.findById(event.getId())).thenReturn(Optional.of(event));
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        when(sessionManager.getLoggedUserId()).thenReturn(user.getId());

        assertDoesNotThrow(() -> eventService.deleteEvent(event.getId()));
        verify(eventRepository, times(1)).delete(event);
    }
}
