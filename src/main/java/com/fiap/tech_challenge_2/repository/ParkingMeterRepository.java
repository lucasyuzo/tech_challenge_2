package com.fiap.tech_challenge_2.repository;

import com.fiap.tech_challenge_2.model.ParkingMeter;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingMeterRepository extends MongoRepository<ParkingMeter, String> { }
