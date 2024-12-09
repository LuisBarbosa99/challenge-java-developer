package br.com.neurotech.challenge.entity.dto;

import br.com.neurotech.challenge.entity.enums.CreditType;

public record ClientDTO(
        String name,
        int age,
        Double income,
        CreditType creditType
) {
}
