package br.com.neurotech.challenge.entity.dto;

import java.util.List;

public record FormErrorDTO(
        int code,
        String message,
        List<FieldErrorDTO> errors
) {
}
