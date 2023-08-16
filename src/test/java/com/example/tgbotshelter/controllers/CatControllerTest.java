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
import com.example.tgbotshelter.services.CatService;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CatControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private CatService catService;
    CatDto catDto = new CatDto("Name", "breed", LocalDate.of(2000, 12, 31), "suit", "gender");

    @Test
    @DisplayName("Проверка создания питомца")
    void createCat() throws Exception {
        mockMvc.perform(post("/cat/new")
                        .content(objectMapper.writeValueAsString(catDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(catService).createCat("Name", "breed", LocalDate.of(2000, 12, 31), "suit", "gender");
    }

    @Test
    @DisplayName("Проверка получения питомца по id")
    void getCat() throws Exception {
        when(catService.getCat(0L)).thenReturn(catDto);
        mockMvc.perform(get("/cat/get").param("id", "0"))
                .andExpect(status().isOk());
        verify(catService).getCat(0L);
    }

    @Test
    @DisplayName("Проверка получения всех питомцев")
    void getAll() throws Exception {
        when(catService.getAllCats()).thenReturn(List.of(catDto));
        mockMvc.perform(get("/cat/all"))
                .andExpect(status().isOk());
        verify(catService).getAllCats();
    }

    @Test
    @DisplayName("Проверка получения всех питомцев владельца")
    void getAllCatsByOwnerId() throws Exception {
        when(catService.getAllCats(0L)).thenReturn(List.of(catDto));
        mockMvc.perform(get("/cat/oid").param("ownerId", "0"))
                .andExpect(status().isOk());
        verify(catService).getAllCats(0L);
    }

    @Test
    @DisplayName("Проверка удаления питомца по id")
    void deleteCat() throws Exception {
        mockMvc.perform(delete("/cat/del").param("id", "0"))
                .andExpect(status().isOk());
        verify(catService).deleteCat(0L);
    }
}