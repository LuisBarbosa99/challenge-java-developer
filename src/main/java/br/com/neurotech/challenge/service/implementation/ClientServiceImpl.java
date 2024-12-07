package br.com.neurotech.challenge.service.implementation;

import br.com.neurotech.challenge.entity.NeurotechClient;
import br.com.neurotech.challenge.service.ClientService;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService {
    @Override
    public String save(NeurotechClient client) {
        return "";
    }

    @Override
    public NeurotechClient get(String id) {
        return null;
    }
}
