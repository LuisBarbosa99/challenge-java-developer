package br.com.neurotech.challenge.service.implementation;

import br.com.neurotech.challenge.entity.dto.CheckCreditDTO;
import br.com.neurotech.challenge.entity.dto.ClientDTO;
import br.com.neurotech.challenge.entity.dto.ClientMinifiedDTO;
import br.com.neurotech.challenge.entity.enums.CreditType;
import br.com.neurotech.challenge.entity.form.NeurotechClient;
import br.com.neurotech.challenge.entity.enums.VehicleModel;
import br.com.neurotech.challenge.exception.ClientNotFoundException;
import br.com.neurotech.challenge.service.ClientService;
import br.com.neurotech.challenge.service.CreditService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

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
            case HATCH -> this.validateHatch(client.getIncome());
            case SUV -> this.validateSUV(client);
        };
        return new CheckCreditDTO(isAvailable);
    }

    private boolean validateSUV(NeurotechClient client) {
        return client.getAge() >= 20 && client.getIncome() > 8000.00;
    }

    private boolean validateHatch(Double income) {
        return income > 5000.00 && income < 15000.00;
    }

    @Override
    public List<ClientMinifiedDTO> listAvailableForHatch() {
        var clients = clientService.findAll();

        if (clients.isEmpty()) {
            return Collections.emptyList();
        }

        return clients.stream()
                .filter(client -> client.age() > 23 && client.age() <= 49)
                .filter(client -> client.creditType() == CreditType.FIXED)
                .filter(client -> validateHatch(client.income()))
                .map(item -> new ClientMinifiedDTO(item.name(), item.income()))
                .toList();
    }
}
