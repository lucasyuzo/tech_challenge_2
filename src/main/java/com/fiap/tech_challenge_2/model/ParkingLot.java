package com.fiap.tech_challenge_2.model;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.bson.codecs.jsr310.LocalDateTimeCodec;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.time.LocalTime;

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

    private ParkingLotStatus status;

    private LocalTime parkingTime;

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

    public void setParkingLotStatus() {
        if (this.isParkingLotEnded()) {
            return;
        }
        if (!this.isParkingLotExpired()) {
            this.status = ParkingLotStatus.IN_PROGRESS;
        } else {
            this.status = ParkingLotStatus.EXPIRED;
        }
    }

    public void setParkingLotStatus(ParkingLotStatus status) {
        this.status = status;
    }

    public Boolean isParkingLotInProgress() {
        return LocalDateTime.now().isBefore(this.endTime);
    }

    public Boolean isParkingLotExpired() {
        return LocalDateTime.now().isAfter(this.endTime);
    }

    public Boolean isParkingLotEnded() {
        return this.status == ParkingLotStatus.ENDED;
    }

    public Integer getParkingTime() {
        LocalDateTime currentTime = LocalDateTime.now();
        return currentTime.compareTo(this.initialTime);
    }
}
