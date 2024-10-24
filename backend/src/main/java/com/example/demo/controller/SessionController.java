package com.example.demo.controller;

import com.example.demo.configuration.SessionManager;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class SessionController {

    private final SessionManager sessionManager;
    private final UserRepository userRepository;

//    TODO: DELETE IF AFTER ADD SPRING SECURITY
    @GetMapping("/")
    public ResponseEntity<String> setSession() {
        List<User> users = userRepository.findAll();

        if (users.isEmpty()) {
            return ResponseEntity.ok("Cannot find any user");
        }

        Long userId = users.get(0).getId();
        sessionManager.setUserIdInSession(userId);

        return ResponseEntity.ok("User ID set in session: " + userId);
    }

}
