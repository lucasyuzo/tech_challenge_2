package com.fiap.tech_challenge_2.service.impl;

import com.fiap.tech_challenge_2.model.ParkingLot;
import com.fiap.tech_challenge_2.model.ParkingMeterCustomSearch;
import com.fiap.tech_challenge_2.model.ParkingMeter;
import com.fiap.tech_challenge_2.service.SearchService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SearchServiceImpl implements SearchService {

    private final MongoTemplate mongoTemplate;

    public SearchServiceImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public ResponseEntity<Page<ParkingMeter>> searchParkingMeterByState(String state) {
        Query query = new Query(
                Criteria
                        .where("address.state")
                        .is(state)
        );
        List<ParkingMeter> result = this.mongoTemplate.find(query, ParkingMeter.class);
        Page<ParkingMeter> page = new PageImpl<ParkingMeter>(result);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(page);
    }

    @Override
    public ResponseEntity<Page<ParkingMeter>> searchParkingMeterByCity(String city) {
        Query query = new Query(
                Criteria
                        .where("address.city")
                        .is(city)
        );
        List<ParkingMeter> result = this.mongoTemplate.find(query, ParkingMeter.class);
        Page<ParkingMeter> page = new PageImpl<ParkingMeter>(result);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(page);
    }

    @Override
    public ResponseEntity<Page<ParkingMeter>> searchParkingMeterByNeighborhood(String neighborhood) {
        Query query = new Query(
                Criteria
                        .where("address.neighborhood")
                        .is(neighborhood)
        );
        List<ParkingMeter> result = this.mongoTemplate.find(query, ParkingMeter.class);
        Page<ParkingMeter> page = new PageImpl<ParkingMeter>(result);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(page);
    }

    @Override
    public ResponseEntity<Page<ParkingMeter>> searchParkingMeterByStreet(String street) {
        Query query = new Query(
                Criteria
                        .where("address.street")
                        .is(street)
        );
        List<ParkingMeter> result = this.mongoTemplate.find(query, ParkingMeter.class);
        Page<ParkingMeter> page = new PageImpl<ParkingMeter>(result);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(page);
    }

    @Override
    public ResponseEntity<Page<ParkingMeter>> searchParkingMeterByZipCode(String zipCode) {
        Query query = new Query(
                Criteria
                        .where("address.zipCode")
                        .is(zipCode)
        );
        List<ParkingMeter> result = this.mongoTemplate.find(query, ParkingMeter.class);
        Page<ParkingMeter> page = new PageImpl<ParkingMeter>(result);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(page);
    }

    @Override
    public ResponseEntity<Page<ParkingMeter>> searchParkingMeterByCustomObject(ParkingMeterCustomSearch parkingMeterCustomSearch) {
        Criteria criteria = new Criteria();
        if (parkingMeterCustomSearch.getState() != null) {
            criteria.and("address.state").is(parkingMeterCustomSearch.getState());
        }
        if (parkingMeterCustomSearch.getCity() != null) {
            criteria.and("address.city").is(parkingMeterCustomSearch.getCity());
        }
        if (parkingMeterCustomSearch.getNeighborhood() != null) {
            criteria.and("address.neighborhood").is(parkingMeterCustomSearch.getNeighborhood());
        }
        if (parkingMeterCustomSearch.getStreet() != null) {
            criteria.and("address.street").is(parkingMeterCustomSearch.getStreet());
        }
        if (parkingMeterCustomSearch.getZipCode() != null) {
            criteria.and("address.zipCode").is(parkingMeterCustomSearch.getZipCode());
        }
        if (parkingMeterCustomSearch.getParked()) {
            criteria.and("parkingLot").ne(null);
            if (parkingMeterCustomSearch.getBrand() != null) {
                criteria.and("parkingLot.car.brand").is(parkingMeterCustomSearch.getBrand());
            }
            if (parkingMeterCustomSearch.getModel() != null) {
                criteria.and("parkingLot.car.model").is(parkingMeterCustomSearch.getModel());
            }
            if (parkingMeterCustomSearch.getPlate() != null) {
                criteria.and("parkingLot.car.plate").is(parkingMeterCustomSearch.getPlate());
            }
            if (parkingMeterCustomSearch.getFirstName() != null) {
                criteria.and("parkingLot.car.owner.firstName").is(parkingMeterCustomSearch.getFirstName());
            }
            if (parkingMeterCustomSearch.getLastName() != null) {
                criteria.and("parkingLot.car.owner.lastName").is(parkingMeterCustomSearch.getLastName());
            }
            if (parkingMeterCustomSearch.getCpf() != null) {
                criteria.and("parkingLot.car.owner.cpf").is(parkingMeterCustomSearch.getCpf());
            }
            if (parkingMeterCustomSearch.getInitialDate() != null && parkingMeterCustomSearch.getEndDate() != null) {
                criteria.and("parkingLot.initialTime").gte(parkingMeterCustomSearch.getInitialDate());
                criteria.and("parkingLot.endTime").lte(parkingMeterCustomSearch.getEndDate());
            } else if (parkingMeterCustomSearch.getInitialDate() != null) {
                criteria.and("parkingLot.initialTime").gte(parkingMeterCustomSearch.getInitialDate());
            } else if (parkingMeterCustomSearch.getEndDate() != null) {
                criteria.and("parkingLot.endTime").is(parkingMeterCustomSearch.getEndDate());
            }
        } else {
            criteria.and("parkingLot").isNull();
        }
        Query query = new Query(criteria);
        List<ParkingMeter> result = this.mongoTemplate.find(query, ParkingMeter.class);
        Page<ParkingMeter> page = new PageImpl<ParkingMeter>(result);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(page);
    }

    @Override
    public ResponseEntity<Page<ParkingLot>> searchParkingLotsByParkingMeter(String parkingMeterId, LocalDateTime initialDate, LocalDateTime endDate) {
        Criteria criteria = new Criteria();
        criteria.and("id").is(parkingMeterId);
        if (initialDate != null && endDate != null) {
            criteria.and("initialTime").gte(initialDate);
            criteria.and("endTime").lte(endDate);
        } else if (initialDate != null) {
            criteria.and("initialTime").gte(initialDate);
        } else if (endDate != null) {
            criteria.and("endTime").is(endDate);
        }
        Query query = new Query(criteria);
        List<ParkingLot> result = this.mongoTemplate.find(query, ParkingLot.class);
        Page<ParkingLot> page = new PageImpl<ParkingLot>(result);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(page);
    }
}
