package com.fiap.tech_challenge_2.service.impl;

import com.fiap.tech_challenge_2.model.Address;
import com.fiap.tech_challenge_2.model.ParkingLot;
import com.fiap.tech_challenge_2.model.ParkingMeter;
import com.fiap.tech_challenge_2.repository.ParkingMeterRepository;
import com.fiap.tech_challenge_2.service.ParkingMeterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ParkingMeterServiceImpl implements ParkingMeterService {

    @Autowired
    private ParkingMeterRepository repository;

    @Override
    public ResponseEntity<?> findAll(Pageable pageable) {
        try {
            Page<ParkingMeter> page = this.repository.findAll(pageable);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(page);
        } catch (Exception exception) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error to find all parking meters: " + exception.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> findById(String id) {
        try {
            ParkingMeter parkingMeter = this.repository.findById(id).orElse(null);
            if (parkingMeter == null) {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body("Parking meter not found");
            }
            return ResponseEntity.status(HttpStatus.OK).body(parkingMeter);
        } catch (Exception exception) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error to find parking meter by id: " + exception.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> create(Address address) {
        ParkingMeter parkingMeter = new ParkingMeter(address);
        this.repository.save(parkingMeter);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

    @Override
    public ResponseEntity<?> updateAddress(String id, Address updatedAddress) {
        try {
            ParkingMeter currentParkingMeter = this.repository.findById(id).orElse(null);
            if (currentParkingMeter == null) {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body("Parking meter not found");
            }
            if (currentParkingMeter.getParkingLot() != null) {
                return ResponseEntity
                        .status(HttpStatus.CONFLICT)
                        .body("Unpark first to update");
            }
            currentParkingMeter.setAddress(updatedAddress);
            this.repository.save(currentParkingMeter);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .build();
        } catch (Exception exception) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error to update parking mater address: " + exception.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> deleteById(String id) {
        try {
            ParkingMeter parkingMeter = this.repository.findById(id).orElse(null);
            if (parkingMeter == null) {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body("Parking meter not found");
            }
            if (parkingMeter.getParkingLot() != null) {
                return ResponseEntity
                        .status(HttpStatus.CONFLICT)
                        .body("Unpark first to delete");
            }
            this.repository.deleteById(id);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .build();
        } catch (Exception exception) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error to delete parking meter by id: " + exception.getMessage());
        }
    }
}
