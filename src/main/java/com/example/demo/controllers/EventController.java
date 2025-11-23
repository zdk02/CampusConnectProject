package com.example.demo.controllers;

import com.example.demo.models.Event;
import com.example.demo.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @PostMapping
    public Event createEvent(@RequestBody Event event) {
        return eventService.createEvent(event);
    }

    @GetMapping
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }

    @PutMapping("/{id}")
    public Event updateEvent(@PathVariable int id, @RequestBody Event event) {
        return eventService.updateEvent(id, event);
    }

    @DeleteMapping("/{id}")
    public String deleteEvent(@PathVariable int id) {
        return eventService.deleteEvent(id) ? "Event deleted" : "Not found";
    }

    @GetMapping("/filter")
    public List<Event> filterEvents(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String category) {
        return eventService.filterEvents(keyword, category);
    }
}
