package com.oezkardes.worktime.service;

import java.sql.Time;
import java.time.LocalTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.oezkardes.worktime.model.TimeTracking;
import com.oezkardes.worktime.model.User;
import com.oezkardes.worktime.repository.TimeTrackingRepository;

@Service
public class TimeTrackingService {

    @Autowired
    private TimeTrackingRepository timeTrackingRepository;

    public TimeTracking initTimeTracking(User user) {
        TimeTracking timeTracking = new TimeTracking(user);
        return timeTrackingRepository.saveAndFlush(timeTracking);
    }

    // Update User
    public Optional<TimeTracking> startTime(User user) {
        LocalTime localTime = LocalTime.now();
        return timeTrackingRepository.findByUser(user).map(timeTracker -> {
            timeTracker.setStartTime(localTime);
            return timeTrackingRepository.save(timeTracker);
        });
    }

    // Update User
    public Optional<TimeTracking> endTime(User user) {
        LocalTime localTime = LocalTime.now();
        return timeTrackingRepository.findByUser(user).map(timeTracker -> {
            timeTracker.setEndTime(localTime);
            return timeTrackingRepository.save(timeTracker);
        });
    }

    // Get with id
    public Optional<TimeTracking> getTimeByUser(User user) {
        return timeTrackingRepository.findByUser(user);
    }
}
