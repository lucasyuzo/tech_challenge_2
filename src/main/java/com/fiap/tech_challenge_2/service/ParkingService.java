package com.fiap.tech_challenge_2.service;

import com.fiap.tech_challenge_2.model.Car;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

public interface ParkingService {
    public ResponseEntity<?> park(String parkingMeterId, Car car, LocalDateTime initialTime, LocalDateTime endTime);
    public ResponseEntity<?> unpark(String parkingMeterId);
    public ResponseEntity<?> extendParking(String parkingLotId);
}
