package com.example.demo.services;

import com.example.demo.models.Event;
import java.util.List;

public interface EventService {
    Event createEvent(Event event);
    List<Event> getAllEvents();
    Event updateEvent(int id, Event event);
    boolean deleteEvent(int id);
    List<Event> filterEvents(String keyword, String category);
}
