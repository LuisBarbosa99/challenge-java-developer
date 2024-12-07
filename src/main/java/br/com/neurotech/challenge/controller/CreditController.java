package br.com.neurotech.challenge.controller;

import br.com.neurotech.challenge.entity.VehicleModel;
import br.com.neurotech.challenge.service.CreditService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oracle.svm.core.annotate.Inject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("/credit")
public class CreditController {

    @Inject
    CreditService creditService;

    private ObjectMapper objectMapper;

    public CreditController(CreditService creditService) {
        this.creditService = creditService;
    }

    @GetMapping()
    public ResponseEntity<JsonNode> checkCredit(@RequestParam String clientId, @RequestParam String model) {
        try {
            var isAvailable = creditService.checkCredit(clientId, VehicleModel.valueOf(model));
            return ResponseEntity.ok().body(objectMapper.readTree(String.format("{\"available\": %s}", isAvailable)));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
