package com.fiap.tech_challenge_2.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ParkingMeterCustomSearch {
    private String state;
    private String city;
    private String neighborhood;
    private String street;
    private Integer number;
    private String zipCode;
    private Boolean parked;
    private String brand;
    private String model;
    private String plate;
    private String firstName;
    private String lastName;
    private String cpf;
    private LocalDateTime initialDate;
    private LocalDateTime endDate;
}
