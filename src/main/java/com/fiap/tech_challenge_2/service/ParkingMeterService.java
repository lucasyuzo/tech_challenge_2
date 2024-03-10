package com.fiap.tech_challenge_2.service;

import com.fiap.tech_challenge_2.model.Address;
import com.fiap.tech_challenge_2.model.ParkingLot;
import com.fiap.tech_challenge_2.model.ParkingMeter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ParkingMeterService {
    public ResponseEntity<?> findAll(Pageable pageable);
    public ResponseEntity<?> findById(String id);
    public ResponseEntity<?> create(Address address);
    public ResponseEntity<?> updateAddress(String id, Address address);
    public ResponseEntity<?> deleteById(String id);
}
