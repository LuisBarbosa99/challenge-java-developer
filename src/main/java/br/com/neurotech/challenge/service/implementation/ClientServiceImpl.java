package br.com.neurotech.challenge.service.implementation;

import br.com.neurotech.challenge.entity.Client;
import br.com.neurotech.challenge.entity.CreditType;
import br.com.neurotech.challenge.entity.NeurotechClient;
import br.com.neurotech.challenge.repository.ClientRepository;
import br.com.neurotech.challenge.service.ClientService;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public String save(NeurotechClient client) {
        var entity = new Client();
        entity.setName(client.getName());
        entity.setAge(client.getAge());
        entity.setIncome(client.getIncome());
        entity.setCreditType(this.defineCreditType(client));

        return clientRepository.save(entity).getId();
    }

    @Override
    public NeurotechClient get(String id) {
        return clientRepository.findById(id)
                .map(entity -> {
                    var client = new NeurotechClient();
                    client.setName(entity.getName());
                    client.setAge(entity.getAge());
                    client.setIncome(entity.getIncome());

                    return client;
                })
                .orElse(null); //todo - lançar exception
    }

    private CreditType defineCreditType(NeurotechClient client) {
        if (client.getAge() > 18 && client.getAge() <= 25) {
            return CreditType.FIXED;
        } else if (client.getAge() > 21 && client.getAge() <= 65
                && client.getIncome() > 5000.00 && client.getIncome() <= 15000.00) {
            return CreditType.VARIABLE;
        } else if (client.getAge() > 65) {
            return CreditType.PAYROLL;
        } else {
            throw new IllegalArgumentException("Cliente não se enquadra aos critérios");
        }
    }
}
