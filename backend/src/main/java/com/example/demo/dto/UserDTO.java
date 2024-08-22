package com.example.demo.dto;


public record UserDTO(Long id,
                      String name,
                      String surname,
                      String userName,
                      String email,
                      String phoneNumber,
                      Integer points) {
}
