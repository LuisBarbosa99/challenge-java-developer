package br.com.neurotech.challenge.controller;

import br.com.neurotech.challenge.entity.dto.CheckCreditDTO;
import br.com.neurotech.challenge.entity.dto.ClientMinifiedDTO;
import br.com.neurotech.challenge.entity.enums.VehicleModel;
import br.com.neurotech.challenge.exception.ClientNotFoundException;
import br.com.neurotech.challenge.service.CreditService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CreditController.class)
public class CreditControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreditService creditService;

    @Test
    public void givenCreditController_whenCheckCredit_thenReturnAvailable() throws Exception {
        var id = "1";
        var model = "HATCH";

        when(creditService.checkCredit(id, VehicleModel.valueOf(model)))
                .thenReturn(new CheckCreditDTO(true));
        mockMvc.perform(MockMvcRequestBuilders.get("/credit?clientId={id}&model={model}", id, model))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.available").value("true"));
    }

    @Test
    public void givenCreditController_whenCheckCredit_thenReturnNotAvailable() throws Exception {
        var id = "1";
        var model = "HATCH";

        when(creditService.checkCredit(id, VehicleModel.valueOf(model)))
                .thenReturn(new CheckCreditDTO(false));
        mockMvc.perform(MockMvcRequestBuilders.get("/credit?clientId={id}&model={model}", id, model))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.available").value("false"));
    }

    @Test
    public void givenCreditController_whenCheckCredit_thenReturnClientNotFound() throws Exception {
        var id = "1";
        var model = "HATCH";

        when(creditService.checkCredit(id, VehicleModel.valueOf(model)))
                .thenThrow(new ClientNotFoundException());
        mockMvc.perform(MockMvcRequestBuilders.get("/credit?clientId={id}&model={model}", id, model))
                .andExpect(status().isNotFound());
    }

    @Test
    public void givenCreditController_whenCheckCredit_thenReturnBadRequest() throws Exception {
        var id = "1";
        var model = "SEDAN";

        mockMvc.perform(MockMvcRequestBuilders.get("/credit?clientId={id}&model={model}", id, model))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value(1002))
                .andExpect(jsonPath("$.message").value("Modelo de veículo inválido."));
    }

    @Test
    public void givenCreditController_whenListUnavailableForHatch_thenReturnEmpty() throws Exception {
        when(creditService.listAvailableForHatch()).thenReturn(Collections.emptyList());
        mockMvc.perform(MockMvcRequestBuilders.get("/credit/list"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.clients").isEmpty());
    }

    @Test
    public void givenCreditController_whenListAvailableForHatch_thenReturnEmpty() throws Exception {
        when(creditService.listAvailableForHatch()).thenReturn(List.of(
                new ClientMinifiedDTO("Luis", 10000.00),
                new ClientMinifiedDTO("Barbosa", 12000.00)
        ));
        mockMvc.perform(MockMvcRequestBuilders.get("/credit/list"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.clients").isNotEmpty());
    }
}
