package br.com.neurotech.challenge.service;

import br.com.neurotech.challenge.entity.form.NeurotechClient;
import br.com.neurotech.challenge.entity.enums.VehicleModel;
import br.com.neurotech.challenge.service.implementation.CreditServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

public class CreditServiceTest {

    @InjectMocks
    private CreditServiceImpl creditService;

    private ClientService clientService;

    @BeforeEach
    public void setUp() {
        clientService = mock(ClientService.class);
        creditService = new CreditServiceImpl(clientService);
    }

    @Test
    public void givenClientIdAndVehicleModel_whenCheckCreditOfHatchModel_thenReturnFalse() {
        var clientId = "123";
        var model = VehicleModel.HATCH;

        NeurotechClient client = getClient();
        client.setIncome(16000.00); // define valor de renda fora do range

        Mockito.when(clientService.get(clientId))
                .thenReturn(client);

        var result = creditService.checkCredit(clientId, model);
        assertFalse(result.available());
    }

    @Test
    public void givenClientIdAndVehicleModel_whenCheckCreditOfHatchModel_thenReturnTrue() {
        var clientId = "123";
        var model = VehicleModel.HATCH;

        NeurotechClient client = getClient();
        client.setIncome(8000.00); // define valor de renda dentro do range

        Mockito.when(clientService.get(clientId))
                .thenReturn(client);

        var result = creditService.checkCredit(clientId, model);
        assertTrue(result.available());
    }


    @Test
    public void givenClientIdAndVehicleModel_whenCheckCreditOfSuvModelAndAgeUnder20_thenReturnFalse() {
        var clientId = "123";
        var model = VehicleModel.SUV;

        NeurotechClient client = getClient();
        client.setAge(19); // define idade abaixo do aceitável

        Mockito.when(clientService.get(clientId))
                .thenReturn(client);

        var result = creditService.checkCredit(clientId, model);
        assertFalse(result.available());
    }

    @Test
    public void givenClientIdAndVehicleModel_whenCheckCreditOfSuvModelAndIncomeIsLow_thenReturnFalse() {
        var clientId = "123";
        var model = VehicleModel.SUV;

        NeurotechClient client = getClient();
        client.setIncome(2000.00); // define valor de renda abaixo do aceitável

        Mockito.when(clientService.get(clientId))
                .thenReturn(client);

        var result = creditService.checkCredit(clientId, model);
        assertFalse(result.available());
    }

    @Test
    public void givenClientIdAndVehicleModel_whenCheckCreditOfSuvModel_thenReturnTrue() {
        var clientId = "123";
        var model = VehicleModel.SUV;

        Mockito.when(clientService.get(clientId))
                .thenReturn(getClient());

        var result = creditService.checkCredit(clientId, model);
        assertTrue(result.available());
    }

    private NeurotechClient getClient() {
        var client = new NeurotechClient();
        client.setName("João das Neves");
        client.setAge(30);
        client.setIncome(15000.0);

        return client;
    }
}
