package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends BaseRepository<User, Long> {

    Optional<User> findByUserName(String username);

    Optional<User> findByName(String name);

    Optional<User> findByPhoneNumber(String phoneNumber);

    Page<User> findByPointsGreaterThan(Integer points, Pageable page);
}
