package br.com.neurotech.challenge.service;

import br.com.neurotech.challenge.entity.model.Client;
import br.com.neurotech.challenge.entity.form.NeurotechClient;
import br.com.neurotech.challenge.repository.ClientRepository;
import br.com.neurotech.challenge.service.implementation.ClientServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

public class ClientServiceTest {

    @InjectMocks
    private ClientServiceImpl clientService;

    private ClientRepository clientRepository;

    @BeforeEach
    public void setUp() {
        clientRepository = mock(ClientRepository.class);
        clientService = new ClientServiceImpl(clientRepository);
    }

    @Test
    public void givenClientIdWhenGetThenReturnNotNull() {
        String clientId = "123";
        Mockito.when(clientRepository.findById(clientId))
                .thenReturn(Optional.of(getClient()));

        NeurotechClient result = clientService.get(clientId);
        assertNotNull(result);
    }

    private Client getClient() {
        var client = new Client();
        client.setId("123");
        client.setName("João das Neves");
        client.setAge(30);
        client.setIncome(15000.0);

        return client;
    }
}
