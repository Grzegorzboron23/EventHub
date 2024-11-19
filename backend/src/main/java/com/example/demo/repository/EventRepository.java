package com.example.demo.repository;

import com.example.demo.model.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface EventRepository extends BaseRepository<Event, Long> {
    Page<Event> findByNameContainingIgnoreCase(String name, Pageable pageable);

    Optional<Event> findByNameIgnoreCase(String name);

    Page<Event> findByAddress_City(String city, Pageable pageable);

    Page<Event> findByAddress_Country(String country, Pageable pageable);
    Page<Event> findByAddress_CountryContaining(String country, Pageable pageable);
}
