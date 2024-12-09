package br.com.neurotech.challenge.controller;

import br.com.neurotech.challenge.entity.dto.CheckCreditDTO;
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
    })
    @GetMapping
    public ResponseEntity<?> checkCredit(@RequestParam String clientId, @RequestParam String model) {
        try {
            return ok()
                    .body(creditService.checkCredit(clientId, VehicleModel.valueOf(model)));
        } catch (ClientNotFoundException e) {
            return badRequest().body(new ErrorDTO(CLIENT_NOT_FOUND, e.getMessage()));
        } catch (IllegalArgumentException e) {
            return internalServerError().build();
        }
    }
}
