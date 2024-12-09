package br.com.neurotech.challenge.service.implementation;

import br.com.neurotech.challenge.entity.Client;
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
}
