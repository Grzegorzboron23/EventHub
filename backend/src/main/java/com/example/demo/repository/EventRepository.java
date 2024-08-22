package com.example.demo.repository;

import com.example.demo.model.Event;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends BaseRepository<Event,Long> {
}
