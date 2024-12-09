package br.com.neurotech.challenge.entity.model;

import br.com.neurotech.challenge.entity.enums.CreditType;
import lombok.Data;

@Data
public class Client {
    private String id;
    private String name;
    private Integer age;
    private Double income;
    private CreditType creditType;
}

