package com.fiap.tech_challenge_2.model;

import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

@Data
public class Owner {
    private String firstName;
    private String lastName;
    @CPF
    private String cpf;

    public String toString() {
        return "Owner: " + this.firstName + " " + this.lastName + " - " + this.cpf;
    }
}
