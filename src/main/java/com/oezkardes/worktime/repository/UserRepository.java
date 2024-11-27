package com.oezkardes.worktime.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oezkardes.worktime.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

}