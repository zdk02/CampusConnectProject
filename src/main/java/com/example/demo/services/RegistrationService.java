package com.example.demo.services;

import java.util.List;

public interface RegistrationService {
    String joinEvent(int studentId, int eventId);
    String leaveEvent(int studentId, int eventId);
    List<Integer> getEventsOfStudent(int studentId);
    List<Integer> getStudentsOfEvent(int eventId);
}
