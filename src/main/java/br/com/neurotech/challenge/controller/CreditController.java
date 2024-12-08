package br.com.neurotech.challenge.controller;

import br.com.neurotech.challenge.entity.VehicleModel;
import br.com.neurotech.challenge.service.CreditService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/credit")
public class CreditController {

    private final CreditService creditService;

    private final ObjectMapper objectMapper;

    public CreditController(CreditService creditService) {
        this.creditService = creditService;
        this.objectMapper = new ObjectMapper();
    }

    @GetMapping
    public ResponseEntity<JsonNode> checkCredit(@RequestParam String clientId, @RequestParam String model) {
        try {
            var isAvailable = creditService.checkCredit(clientId, VehicleModel.valueOf(model));
            return ResponseEntity.ok().body(objectMapper.readTree(String.format("{\"available\": %s}", String.valueOf(isAvailable))));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
