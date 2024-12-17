package com.oezkardes.worktime.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oezkardes.worktime.model.TimeTracking;
import com.oezkardes.worktime.model.User;
import com.oezkardes.worktime.service.TimeTrackingService;
import com.oezkardes.worktime.service.UserService;

@RestController
@RequestMapping("api/timetracking")
public class TimeTrackingController {

    @Autowired(required = true)
    private TimeTrackingService timeTrackingService;

    @Autowired(required = true)
    private UserService userService;

    // Update User By Id
    @PutMapping("/update/start")
    public ResponseEntity<TimeTracking> startTime(@AuthenticationPrincipal UserDetails loginUser) {
        Optional<TimeTracking> timeTracker = timeTrackingService
                .startTime(userService.getUserbyEmail(loginUser.getUsername()).get());
        return timeTracker.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Update User By Id
    @PutMapping("/update/end")
    public ResponseEntity<TimeTracking> endTime(@AuthenticationPrincipal UserDetails loginUser) {
        Optional<TimeTracking> timeTracker = timeTrackingService
                .endTime(userService.getUserbyEmail(loginUser.getUsername()).get());
        return timeTracker.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Add new User
    @PostMapping("/init")
    public ResponseEntity<TimeTracking> initTime(@AuthenticationPrincipal UserDetails loginUser) {
        Optional<User> user = userService.getUserbyEmail(loginUser.getUsername());
        if (user.isPresent()) {
            User presentUser = user.get();
            TimeTracking createdTracking = timeTrackingService.initTimeTracking(presentUser);
            return ResponseEntity.ok(createdTracking);
        } else {
            return ResponseEntity.ok(null);
        }

    }

}
