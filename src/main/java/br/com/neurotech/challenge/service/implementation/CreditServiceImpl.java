package br.com.neurotech.challenge.service.implementation;

import br.com.neurotech.challenge.entity.dto.CheckCreditDTO;
import br.com.neurotech.challenge.entity.form.NeurotechClient;
import br.com.neurotech.challenge.entity.enums.VehicleModel;
import br.com.neurotech.challenge.exception.ClientNotFoundException;
import br.com.neurotech.challenge.service.ClientService;
import br.com.neurotech.challenge.service.CreditService;
import org.springframework.stereotype.Service;

@Service
public class CreditServiceImpl implements CreditService {

    private final ClientService clientService;

    public CreditServiceImpl(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public CheckCreditDTO checkCredit(String clientId, VehicleModel model) {
        var client = clientService.get(clientId);
        if (client == null) {
            throw new ClientNotFoundException();
        }

        var isAvailable = switch (model) {
            case HATCH -> this.validateHatch(client);
            case SUV -> this.validateSUV(client);
        };
        return new CheckCreditDTO(isAvailable);
    }

    private boolean validateSUV(NeurotechClient client) {
        return client.getAge() >= 20 && client.getIncome() > 8000.00;
    }

    private boolean validateHatch(NeurotechClient client) {
        return client.getIncome() > 5000.00 && client.getIncome() < 15000.00;
    }
}
