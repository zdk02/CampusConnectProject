package com.example.demo.models;

import jakarta.persistence.*;

@Entity
@Table(name = "students")
public class Student {

    @Id
    private int id; // provided by frontend for now

    private String name;

    public Student() {}

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
