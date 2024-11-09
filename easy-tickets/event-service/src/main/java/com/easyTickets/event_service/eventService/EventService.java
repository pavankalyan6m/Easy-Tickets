package com.easyTickets.event_service.eventService;


import com.easyTickets.event_service.MongoSequenceGenerator;
import com.easyTickets.event_service.eventModel.Event;
import com.easyTickets.event_service.eventRepository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    private final EventRepository eventRepository;

    @Autowired
    private MongoSequenceGenerator sequenceGenerator;

    public Event saveEvent(Event event) {
        // Generate the sequential ID before saving the event
        if (event.getId() == 0) { // Check if the ID is not set
            event.setId(sequenceGenerator.generateSequence("event_sequence"));
        }
        return eventRepository.save(event);
    }

    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    // Create an event
    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    // Update an event
    public Event updateEvent(int eventId, Event event) {
        event.setId(eventId);
        return eventRepository.save(event);
    }

    // Get event by ID
    public Optional<Event> getEventById(String eventId) {
        return eventRepository.findById(eventId);
    }

    // Get events by category
    public List<Event> getEventsByCategory(String category) {
        return eventRepository.findByCategoriesContaining(category);
    }

    // Get events by name
    public Optional<Event> getEventByName(String eventName) {
        return eventRepository.findByEventName(eventName);
    }

    // Get events by date
    public List<Event> getEventsByDate(String date) {
        return eventRepository.findByDate(date);
    }

    // Delete event
    public void deleteEvent(String eventId) {
        eventRepository.deleteById(eventId);
    }
}

