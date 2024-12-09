package br.com.neurotech.challenge.repository;

import br.com.neurotech.challenge.entity.model.Client;

import java.util.List;
import java.util.Optional;

public interface ClientRepository {

    Client save(Client client);

    Optional<Client> findById(String id);

    List<Client> findAll();
}
