package com.fiap.tech_challenge_2.controller;

import com.fiap.tech_challenge_2.model.ParkingLot;
import com.fiap.tech_challenge_2.service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/parking")
public class ParkingController {

    @Autowired
    ParkingService service;

    @PostMapping("/park/{parkingMeterId}")
    public ResponseEntity<?> park(@PathVariable String parkingMeterId, @RequestBody ParkingLot parkingLot) {
        return this.service.park(parkingMeterId, parkingLot.getCar(), parkingLot.getInitialTime(), parkingLot.getEndTime());
    }

    @PostMapping("/unpark/{parkingMeterId}")
    public ResponseEntity<?> unpark(@PathVariable String parkingMeterId) {
        return this.service.unpark(parkingMeterId);
    }

    @PostMapping("/extend-parking/{parkingLotId}")
    public ResponseEntity<?> extendParking(@PathVariable String parkingLotId) {
        return this.service.extendParking(parkingLotId);
    }
}
