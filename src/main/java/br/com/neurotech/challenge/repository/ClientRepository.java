package br.com.neurotech.challenge.repository;

import br.com.neurotech.challenge.entity.Client;

import java.util.Optional;

public interface ClientRepository {

    Client save(Client client);

    Optional<Client> findById(String id);
}
