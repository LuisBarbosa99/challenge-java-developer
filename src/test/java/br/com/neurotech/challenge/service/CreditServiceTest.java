package br.com.neurotech.challenge.service;

import br.com.neurotech.challenge.entity.VehicleModel;
import br.com.neurotech.challenge.service.implementation.CreditServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CreditServiceTest {

    @InjectMocks
    private CreditServiceImpl creditService;

    private void setUp() {
        creditService = new CreditServiceImpl();
    }

    @Test
    public void givenClientIdAndVehicleModelWhenCheckCreditThenReturnFalse() {
        setUp();
        String clientId = "123";
        VehicleModel model = VehicleModel.HATCH;
        boolean result = creditService.checkCredit(clientId, model);
        assertTrue(result);
    }
}
