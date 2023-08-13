package com.example.tgbotshelter.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.example.tgbotshelter.model.Cat;
import com.example.tgbotshelter.model.Client;
import com.example.tgbotshelter.model.Volunteer;
import com.example.tgbotshelter.repositories.VolunteerRepository;
import com.example.tgbotshelter.services.VolunteerService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VolunteerServiceTest {
    @InjectMocks
    private VolunteerService volunteerService;
    @Mock
    private VolunteerRepository volunteerRepository;
    @Mock
    private TelegramBot telegramBot;
    Volunteer volunteer = new Volunteer(0L, 0L, "FirstName", "LastName", "7_xxx_xxx_xx_xx");
    Client client = new Client(0L, 0L, "FirstName", "LastName", "7_xxx_xxx_xx_xx");


    @Test
    @DisplayName("Проверка создания волонтёра")
    void createUserTest() {
        verify(volunteerRepository, verificationData -> volunteerService.createVolunteer(volunteer.getId(),
                volunteer.getChatId(),
                volunteer.getFirstName(), volunteer.getLastName(), volunteer.getPhoneNumber())).saveAndFlush(volunteer);
    }

    @Test
    @DisplayName("Проверка получения всех волонтёров")
    void getAllTest() {
        when(volunteerRepository.findAll()).thenReturn(List.of(volunteer));
        assertEquals(List.of(volunteer), volunteerService.getAll());
    }

    @Test
    @DisplayName("Проверка получения волонтёра по номеру телефона")
    void getVolunteerByPhoneNumberTest() {
        when(volunteerRepository.findFirstByPhoneNumber(volunteer.getPhoneNumber())).thenReturn(volunteer);
        assertEquals(volunteer, volunteerService.getVolunteer(volunteer.getPhoneNumber()));
    }

    @Test
    @DisplayName("Проверка получения волонтёра по chatId")
    void getVolunteerByChatIdTest() {
        when(volunteerRepository.getByChatId(0L)).thenReturn(volunteer);
        assertEquals(volunteer, volunteerService.getVolunteer(0L));
    }

    @Test
    @DisplayName("Проверка обновления волонтёра")
    void updateUserTest() {
        when(volunteerRepository.getById(0L)).thenReturn(volunteer);
        verify(volunteerRepository, verificationData -> volunteerService.updateVolunteer(volunteer)).saveAndFlush(volunteer);
    }

    @Test
    @DisplayName("Проверка удаления волонтёра")
    void deleteUserTest() {
        verify(volunteerRepository, verificationData -> volunteerService.deleteVolunteer(0L)).deleteById(volunteer.getId());
    }

    @Test
    @DisplayName("Проверка вызова волонтёра")
    void callVolunteerTest() {
        verify(telegramBot, verificationData -> volunteerService.callVolunteer(volunteer.getPhoneNumber())).execute(new SendMessage(0L, "test"));
    }

    @Test
    @DisplayName("Проверка отправки заявки волонтёру")
    void sendToVolunteerTest() {
        verify(telegramBot, verificationData -> volunteerService.sendToVolunteer(client, new Cat())).execute(new SendMessage(0L,
                "test"));
    }
}