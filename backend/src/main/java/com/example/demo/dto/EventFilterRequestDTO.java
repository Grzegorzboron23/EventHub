package com.example.demo.dto;


import java.time.LocalDateTime;

public record EventFilterRequestDTO(LocalDateTime startDate,
                                    LocalDateTime endDate,
                                    Integer page) {
}
