package br.com.neurotech.challenge.entity.dto;

/**
 * Objeto para retornos de erro da API.
 * @param code código do erro
 * @param message mensagem do erro
 */
public record ErrorDTO(int code, String message) {
}
