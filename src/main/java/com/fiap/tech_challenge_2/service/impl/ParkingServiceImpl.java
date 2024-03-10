package com.fiap.tech_challenge_2.service.impl;

import com.fiap.tech_challenge_2.model.Car;
import com.fiap.tech_challenge_2.model.ParkingLot;
import com.fiap.tech_challenge_2.model.ParkingLotStatus;
import com.fiap.tech_challenge_2.model.ParkingMeter;
import com.fiap.tech_challenge_2.repository.ParkingLotRepository;
import com.fiap.tech_challenge_2.repository.ParkingMeterRepository;
import com.fiap.tech_challenge_2.service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
public class ParkingServiceImpl implements ParkingService {

    @Autowired
    private ParkingMeterRepository parkingMeterRepository;

    @Autowired
    private ParkingLotRepository parkingLotRepository;

    @Override
    public ResponseEntity<?> park(String parkingMeterId, Car car, LocalDateTime initialTime, LocalDateTime endTime) {
        try {
            ParkingMeter parkingMeter = this.findParkingMeterById(parkingMeterId);
            ParkingLot parkingLot = new ParkingLot(car, initialTime, endTime, parkingMeterId);
            parkingLot = this.parkingLotRepository.save(parkingLot);
            parkingMeter.setParkingLot(parkingLot);
            this.parkingMeterRepository.save(parkingMeter);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .build();
        } catch (Exception exception) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error to parking: " + exception.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> unpark(String parkingMeterId) {
        try {
            ParkingMeter parkingMeter = this.findParkingMeterById(parkingMeterId);
            ParkingLot parkingLot = this.findParkingLotById(parkingMeter.getParkingLot().getId());
            parkingLot.setParkingLotStatus(ParkingLotStatus.ENDED);
            Integer parkingTime = parkingLot.getParkingTime();
            this.parkingLotRepository.save(parkingLot);
            parkingMeter.setParkingLot(null);
            this.parkingMeterRepository.save(parkingMeter);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("You parked for " + parkingTime);
        } catch (Exception exception) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error to parking: " + exception.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> extendParking(String parkingLotId) {
        try {
            ParkingLot parkingLot = this.findParkingLotById(parkingLotId);
            LocalDateTime newTime = parkingLot.getEndTime().plusHours(1);
            parkingLot.setEndTime(newTime);
            this.parkingLotRepository.save(parkingLot);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .build();
        } catch (Exception exception) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error to parking: " + exception.getMessage());
        }
    }

    private ParkingMeter findParkingMeterById(String parkingMeterId) {
        return this.parkingMeterRepository.findById(parkingMeterId).orElseThrow(() -> new RuntimeException("Parking meter not found"));
    }

    private ParkingLot findParkingLotById(String parkingLotId) {
        return this.parkingLotRepository.findById(parkingLotId).orElseThrow(() -> new RuntimeException("Parking lot not found"));
    }
}
