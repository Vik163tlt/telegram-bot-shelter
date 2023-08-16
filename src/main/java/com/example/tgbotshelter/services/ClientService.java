package com.example.tgbotshelter.services;

import org.springframework.stereotype.Service;
import com.example.tgbotshelter.exceptions.ClientNotFoundException;
import com.example.tgbotshelter.exceptions.NothingToReadException;
import com.example.tgbotshelter.model.Client;
import com.example.tgbotshelter.repositories.ClientRepository;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Service
public class ClientService {
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public void createClient(Long id, Long chatId, String firstName, String lastName, String phoneNumber) {
        Client client = new Client(id, chatId, firstName, lastName, phoneNumber);
        clientRepository.saveAndFlush(client);
    }

    public Client getClientByChatId(Long chatId) {
        return clientRepository.getByChatId(chatId);
    }

    public List<Client> getAll() {
        return clientRepository.findAll();
    }

    public void updateClient(Client client) {
        try {
            Client toUpdate = clientRepository.getReferenceById(client.getId());
            toUpdate.setFirstName(client.getFirstName());
            toUpdate.setLastName(client.getLastName());
            toUpdate.setPhoneNumber(client.getPhoneNumber());
            clientRepository.saveAndFlush(toUpdate);
        } catch (RuntimeException e) {
            throw new ClientNotFoundException();
        }
    }

    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }

    public String readFile(String path) {
        try {
            return Files.readString(Paths.get(path), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new NothingToReadException();
        }
    }
}