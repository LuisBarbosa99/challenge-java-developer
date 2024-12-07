package br.com.neurotech.challenge.service;

import br.com.neurotech.challenge.entity.NeurotechClient;
import br.com.neurotech.challenge.service.implementation.ClientServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ClientServiceTest {

    private ClientServiceImpl clientService;

    @BeforeEach
    public void setUp() {
        clientService = new ClientServiceImpl();
    }

    @Test
    public void givenClientIdWhenGetThenReturnNotNull() {
        String clientId = "123";
        NeurotechClient result = clientService.get(clientId);
        assertNotNull(result);
    }
}
