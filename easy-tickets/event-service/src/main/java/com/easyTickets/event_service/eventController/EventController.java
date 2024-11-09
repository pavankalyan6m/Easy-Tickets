package com.easyTickets.event_service.eventController;

import com.easyTickets.event_service.eventModel.Event;
import com.easyTickets.event_service.eventService.EventService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    // Create Event
    @PostMapping
    public Event createEvent(@RequestBody Event event) {
        return eventService.saveEvent(event);
    }

    // Get Event by ID
    @GetMapping("/{eventId}")
    public ResponseEntity<Event> getEventById(@PathVariable String eventId) {
        Optional<Event> event = eventService.getEventById(eventId);
        return event.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Get Events by Category
    @GetMapping("/category/{category}")
    public ResponseEntity<List<Event>> getEventsByCategory(@PathVariable String category) {
        return ResponseEntity.ok(eventService.getEventsByCategory(category));
    }

    // Get Events by Date
    @GetMapping("/date/{date}")
    public ResponseEntity<List<Event>> getEventsByDate(@PathVariable String date) {
        return ResponseEntity.ok(eventService.getEventsByDate(date));
    }

    // Get Event by Name
    @GetMapping("/name/{eventName}")
    public ResponseEntity<Event> getEventByName(@PathVariable String eventName) {
        Optional<Event> event = eventService.getEventByName(eventName);
        return event.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update Event
    @PutMapping("/{eventId}")
    public ResponseEntity<Event> updateEvent(@PathVariable int eventId, @RequestBody @Valid Event event) {
        return ResponseEntity.ok(eventService.updateEvent(eventId, event));
    }

    // Delete Event
    @DeleteMapping("/{eventId}")
    public ResponseEntity<Void> deleteEvent(@PathVariable String eventId) {
        eventService.deleteEvent(eventId);
        return ResponseEntity.noContent().build();
    }
}