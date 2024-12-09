package br.com.neurotech.challenge.entity;

import lombok.Data;

@Data
public class Client {
    private String id;
    private String name;
    private Integer age;
    private Double income;
    private CreditType creditType;
}

