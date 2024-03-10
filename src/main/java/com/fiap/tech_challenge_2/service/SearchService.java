package com.fiap.tech_challenge_2.service;

import com.fiap.tech_challenge_2.model.ParkingLot;
import com.fiap.tech_challenge_2.model.ParkingMeterCustomSearch;
import com.fiap.tech_challenge_2.model.ParkingMeter;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

public interface SearchService {
    public ResponseEntity<Page<ParkingMeter>> searchParkingMeterByState(String state);
    public ResponseEntity<Page<ParkingMeter>> searchParkingMeterByCity(String city);
    public ResponseEntity<Page<ParkingMeter>> searchParkingMeterByNeighborhood(String neighborhood);
    public ResponseEntity<Page<ParkingMeter>> searchParkingMeterByStreet(String street);
    public ResponseEntity<Page<ParkingMeter>> searchParkingMeterByZipCode(String zipCode);
    public ResponseEntity<Page<ParkingMeter>> searchParkingMeterByCustomObject(ParkingMeterCustomSearch parkingMeterCustomSearch);
    public ResponseEntity<Page<ParkingLot>> searchParkingLotsByParkingMeter(String parkingMeterId, LocalDateTime initialDate, LocalDateTime endDate);
}
