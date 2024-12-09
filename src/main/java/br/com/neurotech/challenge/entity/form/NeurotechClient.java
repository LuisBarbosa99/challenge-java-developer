package br.com.neurotech.challenge.entity.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class NeurotechClient {
	@NotBlank(message = "O nome do cliente é obrigatório")
	private String name;
	@NotNull(message = "A idade do cliente é obrigatória")
	private Integer age;
	@NotNull(message = "A renda do cliente é obrigatória")
	private Double income;

}