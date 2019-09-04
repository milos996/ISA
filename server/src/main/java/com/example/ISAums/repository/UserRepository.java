package com.example.ISAums.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.ISAums.model.User;

import java.util.List;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID>{

    User findByEmail(String email);
    List<User> findAllByFirstName(String name);
}
