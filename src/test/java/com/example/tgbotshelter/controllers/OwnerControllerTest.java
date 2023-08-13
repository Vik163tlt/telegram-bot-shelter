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
import com.example.tgbotshelter.dto.CatDto;
import com.example.tgbotshelter.dto.CatOwnerDto;
import com.example.tgbotshelter.dto.DogDto;
import com.example.tgbotshelter.dto.DogOwnerDto;
import com.example.tgbotshelter.model.CatOwner;
import com.example.tgbotshelter.model.Client;
import com.example.tgbotshelter.model.DogOwner;
import com.example.tgbotshelter.services.CatService;
import com.example.tgbotshelter.services.ClientService;
import com.example.tgbotshelter.services.DogService;
import com.example.tgbotshelter.services.OwnerService;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class OwnerControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private OwnerService ownerService;
    @MockBean
    private DogService dogService;
    @MockBean
    private CatService catService;
    @MockBean
    private ClientService clientService;

    DogDto dogDto = new DogDto("Name", "breed", LocalDate.of(2000, 12, 31), "suit", "gender");
    CatDto catDto = new CatDto("Name", "breed", LocalDate.of(2000, 12, 31), "suit", "gender");
    Client client = new Client(0L, 0L, "FirstName", "LastName", "7_xxx_xxx_xx_xx");
    DogOwnerDto dogOwnerDto = new DogOwnerDto(0L, 0L, "FirstName", "LastName", "7_xxx_xxx_xx_xx",
            LocalDateTime.now());
    DogOwner dogOwner = new DogOwner(0L, 0L, "FirstName", "LastName", "7_xxx_xxx_xx_xx",
            LocalDateTime.now());
    CatOwnerDto catOwnerDto = new CatOwnerDto(0L, 0L, "FirstName", "LastName", "7_xxx_xxx_xx_xx",
            LocalDateTime.now());
    CatOwner catOwner = new CatOwner(0L, 0L, "FirstName", "LastName", "7_xxx_xxx_xx_xx",
            LocalDateTime.now());

    @Test
    @DisplayName("Проверка создания владельца собаки")
    void createDogOwner() throws Exception {
        when(clientService.getClientByChatId(0L)).thenReturn(client);
        when(dogService.getDog(0L)).thenReturn(dogDto);
        mockMvc.perform(post("/owner/dog/new")
                        .param("clientChatId", "0")
                        .param("dogId", "0"))
                .andExpect(status().isOk());
        verify(ownerService).createDogOwner(client.getChatId(), 0L);
    }

    @Test
    @DisplayName("Проверка создания владельца кошки")
    void createCatOwner() throws Exception {
        when(clientService.getClientByChatId(0L)).thenReturn(client);
        when(catService.getCat(0L)).thenReturn(catDto);
        mockMvc.perform(post("/owner/cat/new")
                        .param("clientChatId", "0")
                        .param("catId", "0"))
                .andExpect(status().isOk());
        verify(ownerService).createCatOwner(client.getChatId(), 0L);
    }

    @Test
    @DisplayName("Проверка получения владельца собаки по id")
    void getDogOwner() throws Exception {
        when(ownerService.getDogOwner(0L)).thenReturn(dogOwnerDto);
        mockMvc.perform(get("/owner/dog/get").param("id", "0"))
                .andExpect(status().isOk());
        verify(ownerService).getDogOwner(0L);
    }

    @Test
    @DisplayName("Проверка получения владельца кошки по id")
    void getCatOwner() throws Exception {
        when(ownerService.getCatOwner(0L)).thenReturn(catOwnerDto);
        mockMvc.perform(get("/owner/cat/get").param("id", "0"))
                .andExpect(status().isOk());
        verify(ownerService).getCatOwner(0L);
    }

    @Test
    @DisplayName("Проверка изменения владельца собаки")
    void updateDogOwner() throws Exception {
        mockMvc.perform(put("/owner/dog/put")
                        .content(objectMapper.writeValueAsString(dogOwner))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(ownerService).updateDogOwner(dogOwner.getId(), dogOwner.getChatId(), dogOwner.getFirstName(),
                dogOwner.getLastName(), dogOwner.getPhoneNumber(), dogOwner.getProbation());
    }

    @Test
    @DisplayName("Проверка изменения владельца кошки")
    void updateCatOwner() throws Exception {
        mockMvc.perform(put("/owner/cat/put")
                        .content(objectMapper.writeValueAsString(catOwner))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(ownerService).updateCatOwner(catOwner.getId(), catOwner.getChatId(), catOwner.getFirstName(),
                catOwner.getLastName(), catOwner.getPhoneNumber(), catOwner.getProbation());
    }

    @Test
    @DisplayName("Проверка удаления владельца собаки")
    void deleteDogOwner() throws Exception {
        mockMvc.perform(delete("/owner/dog/del")
                        .param("id", "0"))
                .andExpect(status().isOk());
        verify(ownerService).deleteDogOwner(0L);
    }

    @Test
    @DisplayName("Проверка удаления владельца кошки")
    void deleteCatOwner() throws Exception {
        mockMvc.perform(delete("/owner/cat/del").param("id", "0"))
                .andExpect(status().isOk());
        verify(ownerService).deleteCatOwner(0L);
    }
}