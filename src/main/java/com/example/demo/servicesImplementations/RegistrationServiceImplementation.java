package com.example.demo.servicesImplementations;

import com.example.demo.models.*;
import com.example.demo.repositories.*;
import com.example.demo.services.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistrationServiceImplementation implements RegistrationService {

    @Autowired
    private StudentRepository studentRepo;

    @Autowired
    private EventRepository eventRepo;

    @Autowired
    private RegistrationRepository registrationRepo;

    @Override
    public String joinEvent(int studentId, int eventId) {

        Student student = studentRepo.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Event event = eventRepo.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        if (registrationRepo.existsByStudentAndEvent(student, event))
            return "Already joined";

        registrationRepo.save(new Registration(student, event));
        return "Joined event successfully";
    }

    @Override
    public String leaveEvent(int studentId, int eventId) {

        Student student = studentRepo.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Event event = eventRepo.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        if (!registrationRepo.existsByStudentAndEvent(student, event))
            return "Not registered in this event";

        registrationRepo.deleteByStudentAndEvent(student, event);
        return "Left event successfully";
    }

    @Override
    public List<Integer> getEventsOfStudent(int studentId) {
        Student student = studentRepo.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        return registrationRepo.findByStudent(student)
                .stream().map(r -> r.getEvent().getId()).toList();
    }

    @Override
    public List<Integer> getStudentsOfEvent(int eventId) {
        Event event = eventRepo.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        return registrationRepo.findByEvent(event)
                .stream().map(r -> r.getStudent().getId()).toList();
    }
}
