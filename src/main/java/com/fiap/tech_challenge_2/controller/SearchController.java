package com.fiap.tech_challenge_2.controller;

import com.fiap.tech_challenge_2.model.ParkingLot;
import com.fiap.tech_challenge_2.model.ParkingMeter;
import com.fiap.tech_challenge_2.model.ParkingMeterCustomSearch;
import com.fiap.tech_challenge_2.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private SearchService searchService;

    @GetMapping("/parking-meter/by-state/{state}")
    public ResponseEntity<Page<ParkingMeter>> searchParkingMeterByState(@PathVariable String state) {
        return this.searchService.searchParkingMeterByState(state);
    }

    @GetMapping("/parking-meter/by-city/{city}")
    public ResponseEntity<Page<ParkingMeter>> searchParkingMeterByCity(@PathVariable String city) {
        return this.searchService.searchParkingMeterByCity(city);
    }

    @GetMapping("/parking-meter/by-neighborhood/{neighborhood}")
    public ResponseEntity<Page<ParkingMeter>> searchParkingMeterByNeighborhood(@PathVariable String neighborhood) {
        return this.searchService.searchParkingMeterByNeighborhood(neighborhood);
    }

    @GetMapping("/parking-meter/by-street/{street}")
    public ResponseEntity<Page<ParkingMeter>> searchParkingMeterByStreet(@PathVariable String street) {
        return this.searchService.searchParkingMeterByStreet(street);
    }

    @GetMapping("/parking-meter/by-zipCode/{zipCode}")
    public ResponseEntity<Page<ParkingMeter>> searchParkingMeterByZipCode(@PathVariable String zipCode) {
        return this.searchService.searchParkingMeterByZipCode(zipCode);
    }

    @GetMapping("/parking-meter/by-custom-object")
    public ResponseEntity<Page<ParkingMeter>> searchParkingMeterByCustomObject(@RequestBody ParkingMeterCustomSearch parkingMeterCustomSearch) {
        return this.searchService.searchParkingMeterByCustomObject(parkingMeterCustomSearch);
    }

    @GetMapping("/parking-lots/by-parking-meter-id/{parkingMeterId}")
    public ResponseEntity<Page<ParkingLot>> searchParkingLotsByParkingMeterId(@PathVariable String parkingMeterId) {
        return this.searchService.searchParkingLotsByParkingMeterId(parkingMeterId);
    }
}
