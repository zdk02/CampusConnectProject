package com.example.demo.models;

import jakarta.persistence.*;

@Entity
@Table(name = "registrations")
public class Registration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private Student student;

    @ManyToOne
    private Event event;

    public Registration() {}

    public Registration(Student student, Event event) {
        this.student = student;
        this.event = event;
    }

    public int getId() { return id; }
    public Student getStudent() { return student; }
    public Event getEvent() { return event; }

    public void setStudent(Student student) { this.student = student; }
    public void setEvent(Event event) { this.event = event; }
}
