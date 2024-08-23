package com.example.demo.repository;

import com.example.demo.model.Event;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EventRepository extends BaseRepository<Event, Long> {

    @Query(value = "from Event t WHERE startDate BETWEEN :startDate AND :endDate")
    public List<Event> getAllBetweenDates(@Param("startDate") LocalDateTime startDate,
                                          @Param("endDate") LocalDateTime endDate,
                                          Pageable pageable);
}
