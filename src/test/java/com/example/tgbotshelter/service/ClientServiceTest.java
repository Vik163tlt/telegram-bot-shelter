package com.example.tgbotshelter.service;

import com.pengrad.telegrambot.TelegramBot;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.example.tgbotshelter.exceptions.ClientNotFoundException;
import com.example.tgbotshelter.model.Client;
import com.example.tgbotshelter.repositories.ClientRepository;
import com.example.tgbotshelter.repositories.VolunteerRepository;
import com.example.tgbotshelter.services.ClientService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClientServiceTest {
    @InjectMocks
    private ClientService clientService;
    @Mock
    private VolunteerRepository volunteerRepository;
    @Mock
    private ClientRepository clientRepository;
    @Mock
    private TelegramBot telegramBot;
    Client client = new Client(0L, 0L, "FirstName", "LastName", "7_xxx_xxx_xx_xx");

    @Test
    @DisplayName("Проверка создания клиента")
    void createClient() {
        verify(clientRepository, verificationData -> clientService.createClient(client.getId(), client.getChatId(),
                client.getFirstName(), client.getLastName(), client.getPhoneNumber())).saveAndFlush(client);
    }

    @Test
    @DisplayName("Проверка получения клиента по chatId")
    void getUserByChatId() {
        when(clientRepository.getByChatId(0L)).thenReturn(client);
        assertEquals(client, clientService.getClientByChatId(0L));
    }

    @Test
    @DisplayName("Проверка получения всех клиентов")
    void getAll() {
        when(clientRepository.findAll()).thenReturn(List.of(client));
        assertEquals(List.of(client), clientService.getAll());
    }

    @Test
    @DisplayName("Проверка обновления клиента")
    void updateUser() {
        when(clientRepository.getReferenceById(0L)).thenReturn(client);
        verify(clientRepository, verificationData -> clientService.updateClient(client)).saveAndFlush(client);
    }

    @Test
    @DisplayName("Проверка удаления клиента")
    void deleteUser() {
        verify(clientRepository, verificationData -> clientService.deleteClient(0L)).deleteById(0L);
    }

    @Test
    @DisplayName("Проверка исключения при получении несуществующего клиента")
    public void getClientNotFoundExceptionTest() {
        when(clientRepository.getByChatId(0L)).thenThrow(ClientNotFoundException.class);
        assertThrows(ClientNotFoundException.class,
                () -> when(clientService.getClientByChatId(0L)).thenThrow(ClientNotFoundException.class));
    }

    @Test
    @DisplayName("Проверка считывания из файла")
    void readFile() {
        assertEquals("test", clientService.readFile("src/main/resources/draw/test.txt"));
    }
}