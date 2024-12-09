package br.com.neurotech.challenge.entity.dto;

/**
 * Objeto para mapear erros de campos do formulário.
 * @param field campo com erro
 * @param message mensagem de erro
 */
public record FieldErrorDTO(
    String field,
    String message
) {
}
