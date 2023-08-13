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
import com.example.tgbotshelter.dto.DogDto;
import com.example.tgbotshelter.services.DogService;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class DogControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private DogService dogService;
    DogDto dogDto = new DogDto("Name", "breed", LocalDate.of(2000, 12, 31), "suit", "gender");

    @Test
    @DisplayName("Проверка создания питомца")
    void createDog() throws Exception {
        mockMvc.perform(post("/dog/new")
                        .content(objectMapper.writeValueAsString(dogDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(dogService).createDog("Name", "breed", LocalDate.of(2000, 12, 31), "suit", "gender");
    }

    @Test
    @DisplayName("Проверка получения питомца по id")
    void getDog() throws Exception {
        when(dogService.getDog(0L)).thenReturn(dogDto);
        mockMvc.perform(get("/dog/get").param("id", "0"))
                .andExpect(status().isOk());
        verify(dogService).getDog(0L);
    }

    @Test
    @DisplayName("Проверка получения всех питомцев")
    void getAll() throws Exception {
        when(dogService.getAllDogs()).thenReturn(List.of(dogDto));
        mockMvc.perform(get("/dog/all"))
                .andExpect(status().isOk());
        verify(dogService).getAllDogs();
    }

    @Test
    @DisplayName("Проверка получения всех питомцев владельца")
    void getAllDogsByOwnerId() throws Exception {
        when(dogService.getAllDogs(0L)).thenReturn(List.of(dogDto));
        mockMvc.perform(get("/dog/oid").param("ownerId", "0"))
                .andExpect(status().isOk());
        verify(dogService).getAllDogs(0L);
    }

    @Test
    @DisplayName("Проверка удаления питомца по id")
    void deleteDog() throws Exception {
        mockMvc.perform(delete("/dog/del").param("id", "0"))
                .andExpect(status().isOk());
        verify(dogService).deleteDog(0L);
    }
}