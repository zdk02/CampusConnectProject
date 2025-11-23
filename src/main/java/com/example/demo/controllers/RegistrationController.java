package com.example.demo.controllers;

import com.example.demo.services.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @PostMapping("/join")
    public String joinEvent(@RequestParam int studentId, @RequestParam int eventId) {
        return registrationService.joinEvent(studentId, eventId);
    }

    @PostMapping("/leave")
    public String leaveEvent(@RequestParam int studentId, @RequestParam int eventId) {
        return registrationService.leaveEvent(studentId, eventId);
    }

    @GetMapping("/events/{studentId}")
    public List<Integer> getEvents(@PathVariable int studentId) {
        return registrationService.getEventsOfStudent(studentId);
    }

    @GetMapping("/students/{eventId}")
    public List<Integer> getStudents(@PathVariable int eventId) {
        return registrationService.getStudentsOfEvent(eventId);
    }
}
