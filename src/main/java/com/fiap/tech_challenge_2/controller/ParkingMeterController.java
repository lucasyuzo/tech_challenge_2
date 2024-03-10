package com.fiap.tech_challenge_2.controller;

import com.fiap.tech_challenge_2.model.Address;
import com.fiap.tech_challenge_2.model.ParkingLot;
import com.fiap.tech_challenge_2.model.ParkingMeter;
import com.fiap.tech_challenge_2.service.ParkingMeterService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/parking-meter")
public class ParkingMeterController {

    @Autowired
    private ParkingMeterService service;

    @GetMapping
    public ResponseEntity<?> findAll(@PageableDefault(size = 10, page = 0) Pageable pageable) {
        return this.service.findAll(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable String id) {
        return this.service.findById(id);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Address address) {
        return this.service.create(address);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAddress(
            @PathVariable String id,
            @RequestBody Address updatedAddress
    ) {
        return this.service.updateAddress(id, updatedAddress);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        return this.service.deleteById(id);
    }
}
