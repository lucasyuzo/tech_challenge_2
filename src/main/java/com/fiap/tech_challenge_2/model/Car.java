package com.fiap.tech_challenge_2.model;

import lombok.Data;

@Data
public class Car {
    private String brand;
    private String model;
    private String plate;
    private Owner owner;
}
