package br.com.neurotech.challenge.controller;

import br.com.neurotech.challenge.entity.NeurotechClient;
import br.com.neurotech.challenge.service.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

import static org.springframework.http.ResponseEntity.created;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/client")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody NeurotechClient form) {
        String id = clientService.save(form);
        var uri = URI.create(STR."http://localhost:5000/client/\{id}");
        return created(uri)
                .build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<NeurotechClient> get(@PathVariable String id) {
        return ok(clientService.get(id));
    }
}
