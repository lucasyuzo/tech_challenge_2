package com.fiap.tech_challenge_2.repository;

import com.fiap.tech_challenge_2.model.ParkingLot;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingLotRepository extends MongoRepository<ParkingLot, String> { }
