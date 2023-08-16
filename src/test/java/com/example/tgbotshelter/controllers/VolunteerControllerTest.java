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
import com.example.tgbotshelter.model.Volunteer;
import com.example.tgbotshelter.services.VolunteerService;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class VolunteerControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private VolunteerService volunteerService;
    Volunteer volunteer = new Volunteer(0L, 0L, "FirstName", "LastName", "7_xxx_xxx_xx_xx");

    @Test
    @DisplayName("Проверка получения волонтёра по phoneNumber")
    void getVolunteer() throws Exception {
        when(volunteerService.getVolunteer("7_xxx_xxx_xx_xx")).thenReturn(volunteer);
        mockMvc.perform(get("/v/get").param("phoneNumber", "7_xxx_xxx_xx_xx"))
                .andExpect(status().isOk());
        verify(volunteerService).getVolunteer("7_xxx_xxx_xx_xx");
    }

    @Test
    @DisplayName("Проверка получения всех волонтёров")
    void getAll() throws Exception {
        when(volunteerService.getAll()).thenReturn(List.of(volunteer));
        mockMvc.perform(get("/v/all"))
                .andExpect(status().isOk());
        verify(volunteerService).getAll();
    }

    @Test
    @DisplayName("Проверка изменения волонтёра")
    void updateVolunteer() throws Exception {
        mockMvc.perform(put("/v/put")
                        .content(objectMapper.writeValueAsString(volunteer))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(volunteerService).updateVolunteer(volunteer);
    }

    @Test
    @DisplayName("Проверка удаления волонтёра")
    void deleteVolunteer() throws Exception {
        mockMvc.perform(delete("/v/del").param("id", "0"))
                .andExpect(status().isOk());
        verify(volunteerService).deleteVolunteer(0L);
    }
}