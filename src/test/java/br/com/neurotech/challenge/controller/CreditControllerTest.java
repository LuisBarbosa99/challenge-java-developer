package br.com.neurotech.challenge.controller;

import br.com.neurotech.challenge.entity.CheckCreditDTO;
import br.com.neurotech.challenge.entity.VehicleModel;
import br.com.neurotech.challenge.service.CreditService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

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
}
