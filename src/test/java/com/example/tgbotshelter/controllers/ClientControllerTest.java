package com.example.tgbotshelter.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.example.tgbotshelter.model.Client;
import com.example.tgbotshelter.services.ClientService;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ClientControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private ClientService clientService;
    Client client = new Client(0L, 0L, "FirstName", "LastName", "7_xxx_xxx_xx_xx");

    @Test
    @DisplayName("Проверка получения клиента по chatId")
    void getClient() throws Exception {
        when(clientService.getClientByChatId(0L)).thenReturn(client);
        mockMvc.perform(get("/c/get").param("chatId", "0"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(0L));
        verify(clientService).getClientByChatId(0L);
    }

    @Test
    @DisplayName("Проверка получения всех клиентов")
    void getAll() throws Exception {
        when(clientService.getAll()).thenReturn(List.of(client));
        mockMvc.perform(get("/c/all"))
                .andExpect(status().isOk());
        verify(clientService).getAll();
    }

    @Test
    @DisplayName("Проверка обновления клиента")
    void updateClient() throws Exception {
        when(clientService.getClientByChatId(0L)).thenReturn(client);
        mockMvc.perform(put("/c/put")
                        .content(objectMapper.writeValueAsString(client))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(clientService).updateClient(client);
    }

    @Test
    @DisplayName("Проверка удаления клиента")
    void deleteClient() throws Exception {
        mockMvc.perform(delete("/c/del").param("id", "0"))
                .andExpect(status().isOk());
        verify(clientService).deleteClient(0L);
    }
}