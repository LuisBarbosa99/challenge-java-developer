package br.com.neurotech.challenge.controller;

import br.com.neurotech.challenge.entity.form.NeurotechClient;
import br.com.neurotech.challenge.service.ClientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ClientController.class)
public class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClientService clientService;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void givenClientController_whenSave_thenReturnCreated() throws Exception {
        var client = this.getClient();
        var id = "1";
        Mockito.when(clientService.save(client))
                .thenReturn(id);

        mockMvc.perform(MockMvcRequestBuilders.post("/client")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(client)))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(header().string("Location", STR."http://localhost:5000/client/\{id}"));
    }

    @Test
    public void givenClientController_whenGet_thenReturnOk() throws Exception {
        var client = this.getClient();
        var id = "1";
        Mockito.when(clientService.get(id))
                .thenReturn(client);

        mockMvc.perform(MockMvcRequestBuilders.get("/client/{id}", id))
                .andExpect(status().isOk());
    }

    private NeurotechClient getClient() {
        var client = new NeurotechClient();
        client.setName("João das Neves");
        client.setAge(30);
        client.setIncome(15000.0);

        return client;
    }
}
