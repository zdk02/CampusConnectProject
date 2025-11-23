package com.example.demo.servicesImplementations;

import com.example.demo.models.Event;
import com.example.demo.repositories.EventRepository;
import com.example.demo.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImplementation implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Override
    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    @Override
    public Event updateEvent(int id, Event updatedEvent) {

        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        event.setName(updatedEvent.getName());
        event.setDescription(updatedEvent.getDescription());
        event.setLocation(updatedEvent.getLocation());
        event.setDate(updatedEvent.getDate());
        event.setCategory(updatedEvent.getCategory());

        return eventRepository.save(event);
    }

    @Override
    public boolean deleteEvent(int id) {
        if (!eventRepository.existsById(id)) return false;
        eventRepository.deleteById(id);
        return true;
    }

    @Override
    public List<Event> filterEvents(String keyword, String category) {

        if (keyword != null && category != null) {
            return eventRepository.findAll().stream()
                    .filter(e -> e.getName().toLowerCase().contains(keyword.toLowerCase()) &&
                                 e.getCategory().equalsIgnoreCase(category))
                    .toList();
        }

        if (keyword != null) return eventRepository.findByNameContainingIgnoreCase(keyword);
        if (category != null) return eventRepository.findByCategoryIgnoreCase(category);

        return eventRepository.findAll();
    }
}
