package com.fiap.tech_challenge_2.model;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class Address {
    @NotNull(message = "state field can't be null")
    private String state;

    @NotNull(message = "city field can't be null")
    private String city;

    @NotNull(message = "neighborhood field can't be null")
    private String neighborhood;

    @NotNull(message = "street field can't be null")
    private String street;

    @NotNull(message = "number field can't be null")
    private Integer number;

    @NotNull(message = "zipCode field can't be null")
    private String zipCode;
}
