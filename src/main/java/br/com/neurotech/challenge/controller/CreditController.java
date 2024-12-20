package br.com.neurotech.challenge.controller;

import br.com.neurotech.challenge.entity.dto.CheckCreditDTO;
import br.com.neurotech.challenge.entity.dto.ClientMinifiedDTO;
import br.com.neurotech.challenge.entity.dto.ClientsAvailableForCreditDTO;
import br.com.neurotech.challenge.entity.dto.ErrorDTO;
import br.com.neurotech.challenge.entity.enums.VehicleModel;
import br.com.neurotech.challenge.exception.ClientNotFoundException;
import br.com.neurotech.challenge.service.CreditService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static br.com.neurotech.challenge.entity.util.Constants.ErrorCodes.CLIENT_NOT_FOUND;
import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.internalServerError;
import static org.springframework.http.ResponseEntity.ok;

@Tag(name = "Credit", description = "Operações de consulta de crédito.")
@RestController
@RequestMapping("/credit")
public class CreditController {

    private final CreditService creditService;

    public CreditController(CreditService creditService) {
        this.creditService = creditService;
    }

    @Operation(
            summary = "Consultar crédito",
            parameters = {
                    @Parameter(name = "clientId", description = "Id do cliente."),
                    @Parameter(name = "model", description = "Modelo do veículo.")
            },
            description = "Consulta o crédito disponível para um cliente."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Crédito consultado com sucesso.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CheckCreditDTO.class)
            )),
            @ApiResponse(
                    responseCode = "400",
                    description = "Erro ao consultar crédito.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class)
            )),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado.")
    })
    @GetMapping
    public ResponseEntity<?> checkCredit(@RequestParam String clientId, @RequestParam String model) {
        return ok().body(creditService.checkCredit(clientId, VehicleModel.valueOf(model)));
    }

    @Operation(
            summary = "Listar clientes disponíveis para crédito.",
            description = "Lista os clientes disponíveis para receber crédito para modelo Hatch."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Clientes listados com sucesso.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ClientsAvailableForCreditDTO.class)
            ))
    })
    @GetMapping("/list")
    public ResponseEntity<?> list() {
        var list = creditService.listAvailableForHatch();
        return ok().body(new ClientsAvailableForCreditDTO(list));
    }
}
