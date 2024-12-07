package br.com.neurotech.challenge.controller;

import br.com.neurotech.challenge.entity.NeurotechClient;
import br.com.neurotech.challenge.service.ClientService;
import com.oracle.svm.core.annotate.Inject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.http.HttpResponse;

import static org.springframework.http.ResponseEntity.created;

@RestController("/client")
public class ClientController {

    @Inject
    ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody NeurotechClient form) {
        String id = clientService.save(form);
        URI uri = URI.create(String.format("http://localhost:8080/client/%s", id));
        return created(uri)
                .build();
    }
}
