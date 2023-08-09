package com.example.tgbotshelter.controllers;

import org.springframework.web.bind.annotation.*;
import com.example.tgbotshelter.model.Client;
import com.example.tgbotshelter.services.ClientService;

import java.util.List;

@RestController
@RequestMapping("/c")
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    /**
     * Получение экземпляра класса
     * @return Client instance
     */
    @GetMapping("/get")
    public Client getClient(@RequestParam Long chatId) {
        return clientService.getClientByChatId(chatId);
    }

    /**
     * Получение списка всех клиентов
     * @return List of Clients
     */
    @GetMapping("/all")
    public List<Client> getAll() {
        return clientService.getAll();
    }

    /**
     * Изменение экземпляра класса Client
     */
    @PutMapping("/put")
    public void updateClient(@RequestBody Client client) {
        clientService.updateClient(client);
    }

    /**
     * Удаление экземпляра класса Client
     */
    @DeleteMapping("/del")
    public void deleteClient(@RequestParam(required = false, name = "id") Long id) {
        clientService.deleteClient(id);
    }
}