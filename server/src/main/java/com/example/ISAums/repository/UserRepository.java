package com.example.ISAums.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.ISAums.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID>{
    boolean existsByEmail(String email);

    boolean existsByPhoneNumber(String phoneNumber);

    User findByEmail(String email);

    List<User> findAllByFirstName(String name);
}
