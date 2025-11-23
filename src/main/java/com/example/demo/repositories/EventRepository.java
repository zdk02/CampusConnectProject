package com.example.demo.repositories;

import com.example.demo.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Integer> {

    List<Event> findByNameContainingIgnoreCase(String keyword);
    List<Event> findByCategoryIgnoreCase(String category);
}
