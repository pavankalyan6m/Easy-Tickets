package com.easyTickets.event_service.eventRepository;


import com.easyTickets.event_service.eventModel.Event;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository extends MongoRepository<Event, String> {
    List<Event> findByCategoriesContaining(String category);

    Optional<Event> findByEventName(String eventName);

    List<Event> findByDate(String date);
}

