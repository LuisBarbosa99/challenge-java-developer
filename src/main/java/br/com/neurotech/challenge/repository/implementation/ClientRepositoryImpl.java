package br.com.neurotech.challenge.repository.implementation;

import br.com.neurotech.challenge.entity.model.Client;
import br.com.neurotech.challenge.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class ClientRepositoryImpl implements ClientRepository {
    /**
     * Hashmap usado para persistir em memória os dados dos clientes.
     */
    private final HashMap<String, Client> clientMap;

    public ClientRepositoryImpl() {
        this.clientMap = new HashMap<String, Client>();
    }

    @Override
    public Client save(Client client) {
        var id = String.valueOf(clientMap.size() + 1);
        client.setId(id);
        clientMap.put(id, client);

        return client;
    }

    @Override
    public Optional<Client> findById(String id) {
        if (clientMap.containsKey(id)) {
            return Optional.of(clientMap.get(id));
        }
        return Optional.empty();
    }

    @Override
    public List<Client> findAll() {
        return new ArrayList<>(clientMap.values());
    }
}
