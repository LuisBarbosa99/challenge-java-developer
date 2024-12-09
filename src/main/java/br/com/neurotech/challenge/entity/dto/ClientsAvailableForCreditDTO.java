package br.com.neurotech.challenge.entity.dto;

import java.util.List;

/**
 * Objeto para retornos de clientes disponíveis para crédito.
 */
public record ClientsAvailableForCreditDTO (
        List<ClientMinifiedDTO> clients
) {
}
