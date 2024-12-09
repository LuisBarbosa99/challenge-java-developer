package br.com.neurotech.challenge.controller;

import br.com.neurotech.challenge.entity.form.NeurotechClient;
import br.com.neurotech.challenge.exception.ClientNotFoundException;
import br.com.neurotech.challenge.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

import static org.springframework.http.ResponseEntity.created;
import static org.springframework.http.ResponseEntity.ok;

@Tag(name = "Client", description = "Operações de consulta e cadastro de clientes.")
@RestController
@RequestMapping("/client")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @Operation(
            summary = "Cadastrar cliente",
            description = "Cadastra um novo cliente."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cliente cadastrado com sucesso."),
            @ApiResponse(responseCode = "400", description = "Erro ao cadastrar cliente.")
    })
    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Valid NeurotechClient form) {
        String id = clientService.save(form);
        var uri = URI.create(STR."http://localhost:5000/client/\{id}");
        return created(uri)
                .build();
    }

    @Operation(
            summary = "Consultar cliente",
            description = "Consulta um cliente pelo id."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Cliente encontrado.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = NeurotechClient.class)
                    )),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado.")
    })
    @GetMapping("/{id}")
    public ResponseEntity<NeurotechClient> get(@PathVariable String id) {
        return ok(clientService.get(id));
    }
}
