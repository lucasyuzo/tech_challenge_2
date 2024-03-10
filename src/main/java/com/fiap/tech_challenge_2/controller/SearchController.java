package com.fiap.tech_challenge_2.controller;

import com.fiap.tech_challenge_2.model.ParkingLot;
import com.fiap.tech_challenge_2.model.ParkingMeter;
import com.fiap.tech_challenge_2.model.ParkingMeterCustomSearch;
import com.fiap.tech_challenge_2.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private SearchService searchService;

    @GetMapping("/by-state/{state}")
    public ResponseEntity<Page<ParkingMeter>> searchParkingMeterByState(@PathVariable String state) {
        return this.searchService.searchParkingMeterByState(state);
    }

    @GetMapping("/by-city/{city}")
    public ResponseEntity<Page<ParkingMeter>> searchParkingMeterByCity(@PathVariable String city) {
        return this.searchService.searchParkingMeterByCity(city);
    }

    @GetMapping("/by-state/{neighborhood}")
    public ResponseEntity<Page<ParkingMeter>> searchParkingMeterByNeighborhood(@PathVariable String neighborhood) {
        return this.searchService.searchParkingMeterByNeighborhood(neighborhood);
    }

    @GetMapping("/by-state/{street}")
    public ResponseEntity<Page<ParkingMeter>> searchParkingMeterByStreet(@PathVariable String street) {
        return this.searchService.searchParkingMeterByStreet(street);
    }

    @GetMapping("/by-state/{zipCode}")
    public ResponseEntity<Page<ParkingMeter>> searchParkingMeterByZipCode(@PathVariable String zipCode) {
        return this.searchService.searchParkingMeterByZipCode(zipCode);
    }

    @GetMapping("/by-custom-object")
    public ResponseEntity<Page<ParkingMeter>> searchParkingMeterByCustomObject(@RequestBody ParkingMeterCustomSearch parkingMeterCustomSearch) {
        return this.searchService.searchParkingMeterByCustomObject(parkingMeterCustomSearch);
    }

    @GetMapping("/parking-lots")
    public ResponseEntity<Page<ParkingLot>> searchParkingLotsByParkingMeter(
            @RequestParam("parkingMeterId") String parkingMeterId,
            @RequestParam("initialDate") LocalDateTime initialDate,
            @RequestParam("endDate") LocalDateTime endDate
    ) {
        return this.searchService.searchParkingLotsByParkingMeter(parkingMeterId, initialDate, endDate);
    }
}
