package com.example.demo.repositories;

import com.example.demo.models.Event;
import com.example.demo.models.Registration;
import com.example.demo.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegistrationRepository extends JpaRepository<Registration, Integer> {

    // Check if a registration exists
    boolean existsByStudentAndEvent(Student student, Event event);

    // Delete a registration
    void deleteByStudentAndEvent(Student student, Event event);

    // Get all registrations of a student
    List<Registration> findByStudent(Student student);

    // Get all registrations of an event
    List<Registration> findByEvent(Event event);
}
