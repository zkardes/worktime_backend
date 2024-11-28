package com.oezkardes.worktime.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oezkardes.worktime.model.User;
import com.oezkardes.worktime.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Create new User
    public User createUser(User user) {
        return userRepository.saveAndFlush(user);
    }

    // Update User
    public Optional<User> updateUser(long id, User updatedUser) {
        return userRepository.findById(id).map(existingUser -> {
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setPassword(updatedUser.getPassword());
            return userRepository.save(existingUser);
        });
    }

    // Get with id
    public Optional<User> getUserbyId(long id) {
        return userRepository.findById(id);
    }

    // Get with email
    public Optional<User> getUserbyEmail(String email) {
        return userRepository.findByEmail(email);
    }

}
