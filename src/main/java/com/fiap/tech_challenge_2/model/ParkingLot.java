package com.fiap.tech_challenge_2.model;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document
public class ParkingLot {
    @Id
    private String id;

    @NotNull
    private Car car;

    @NotNull
    private LocalDateTime initialTime;

    @NotNull
    private LocalDateTime endTime;

    @NotNull
    private String parkingMeterId;

    @Version
    private Long version;

    public ParkingLot(Car car, LocalDateTime initialTime, LocalDateTime endTime, String parkingMeterId) {
        this.setCar(car);
        this.setInitialTime(initialTime);
        this.setEndTime(endTime);
        this.setParkingMeterId(parkingMeterId);
    }
}
