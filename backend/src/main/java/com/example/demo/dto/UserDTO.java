package com.example.demo.dto;


import com.example.demo.model.User;

public record UserDTO(Long id,
                      String name,
                      String surname,
                      String userName,
                      String email,
                      String phoneNumber,
                      Integer points) {
    public UserDTO(User user) {
        this(user.getId(),
                user.getName(),
                user.getSurname(),
                user.getUserName(),
                user.getEmail(),
                user.getPhoneNumber(),
                user.getPoints());
    }
}
