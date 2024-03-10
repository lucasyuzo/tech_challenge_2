package com.fiap.tech_challenge_2.model;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Optional;

@Data
@Document
public class ParkingMeter {
    @Id
    private String id;

    @NotNull(message = "address field can't be null")
    private Address address;

    @DBRef
    private ParkingLot parkingLot;

    @Version
    private Long version;

    public ParkingMeter(Address address) {
        this.setAddress(address);
        this.setParkingLot(null);
    }
}
