package com.oezkardes.worktime.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.oezkardes.worktime.model.TimeTracking;
import com.oezkardes.worktime.model.User;

import java.time.LocalDate;
import java.util.List;


public interface TimeTrackingRepository extends JpaRepository <TimeTracking,Long> {
    
    List<TimeTracking> findByUser(User user);

    List<TimeTracking> findByUserAndDateBetween(User user, LocalDate start, LocalDate end);

}
