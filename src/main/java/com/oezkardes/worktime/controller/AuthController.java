package com.oezkardes.worktime.controller;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.oezkardes.worktime.model.Role;
import com.oezkardes.worktime.model.User;
import com.oezkardes.worktime.repository.UserRepository;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Hash the password
        user.setRole("" + Role.Type.EMPLOYEE);
        userRepository.save(user);
        return ResponseEntity.ok("User registered successfully!");
    }
}
