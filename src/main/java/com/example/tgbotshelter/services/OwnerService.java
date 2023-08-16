package com.example.tgbotshelter.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.tgbotshelter.dto.CatOwnerDto;
import com.example.tgbotshelter.dto.DogOwnerDto;
import com.example.tgbotshelter.exceptions.OwnerNotFoundException;
import com.example.tgbotshelter.model.*;
import com.example.tgbotshelter.repositories.CatOwnerRepository;
import com.example.tgbotshelter.repositories.DogOwnerRepository;
import com.example.tgbotshelter.utils.MappingUtils;

import java.time.LocalDateTime;

@Service
public class OwnerService {
    private final DogOwnerRepository dogOwnerRepository;
    private final CatOwnerRepository catOwnerRepository;
    private final DogService dogService;
    private final CatService catService;
    private final ClientService clientService;
    private final MappingUtils mappingUtils;

    public OwnerService(DogOwnerRepository dogOwnerRepository, CatOwnerRepository catOwnerRepository, DogService dogService, CatService catService, ClientService clientService, MappingUtils mappingUtils) {
        this.dogOwnerRepository = dogOwnerRepository;
        this.catOwnerRepository = catOwnerRepository;
        this.catService = catService;
        this.dogService = dogService;
        this.clientService = clientService;
        this.mappingUtils = mappingUtils;
    }

    public void createDogOwner(Long clientChatId, Long dogId) {
        Dog dog = mappingUtils.mapToDog(dogService.getDog(dogId));
        Client client = clientService.getClientByChatId(clientChatId);
        DogOwner dogOwner = new DogOwner(client.getId(), client.getChatId(), client.getFirstName(),
                client.getLastName(), client.getPhoneNumber(), ReportService.reportingPeriod);
        dog.setDogOwner(dogOwner);
        dogOwnerRepository.saveAndFlush(dogOwner);
        clientService.deleteClient(client.getId());
    }

    public void createCatOwner(Long clientChatId, Long catId) {
        Cat cat = mappingUtils.mapToCat(catService.getCat(catId));
        Client client = clientService.getClientByChatId(clientChatId);
        CatOwner catOwner = new CatOwner(client.getId(), client.getChatId(), client.getFirstName(),
                client.getLastName(), client.getPhoneNumber(), ReportService.reportingPeriod);
        cat.setCatOwner(catOwner);
        catOwnerRepository.saveAndFlush(catOwner);
        clientService.deleteClient(client.getId());
    }

    @Transactional
    public DogOwnerDto getDogOwner(Long chatId) {
        try {
            return mappingUtils.mapToDogOwnerDto(dogOwnerRepository.getByChatId(chatId));
        } catch (RuntimeException e) {
            throw new OwnerNotFoundException();
        }
    }

    @Transactional
    public CatOwnerDto getCatOwner(Long chatId) {
        try {
            return mappingUtils.mapToCatOwnerDto(catOwnerRepository.getByChatId(chatId));
        } catch (RuntimeException e) {
            throw new OwnerNotFoundException();
        }
    }

    public void updateDogOwner(Long id, Long chatId, String firstName, String lastName, String phoneNumber,
                               LocalDateTime probation) {
        try {
            DogOwner toUpdate = dogOwnerRepository.getReferenceById(id);
            toUpdate.setChatId(chatId);
            toUpdate.setFirstName(firstName);
            toUpdate.setLastName(lastName);
            toUpdate.setPhoneNumber(phoneNumber);
            toUpdate.setProbation(probation);
            dogOwnerRepository.saveAndFlush(toUpdate);
        } catch (RuntimeException e) {
            throw new OwnerNotFoundException();
        }
    }

    public void updateCatOwner(Long id, Long chatId, String firstName, String lastName, String phoneNumber,
                               LocalDateTime probation) {
        try {
            CatOwner toUpdate = catOwnerRepository.getReferenceById(id);
            toUpdate.setChatId(chatId);
            toUpdate.setFirstName(firstName);
            toUpdate.setLastName(lastName);
            toUpdate.setPhoneNumber(phoneNumber);
            toUpdate.setProbation(probation);
            catOwnerRepository.saveAndFlush(toUpdate);
        } catch (RuntimeException e) {
            throw new OwnerNotFoundException();
        }
    }

    public void deleteDogOwner(Long id) {
        try {
            dogOwnerRepository.deleteById(id);
        } catch (RuntimeException e) {
            throw new OwnerNotFoundException();
        }
    }

    public void deleteCatOwner(Long id) {
        try {
            catOwnerRepository.deleteById(id);
        } catch (RuntimeException e) {
            throw new OwnerNotFoundException();
        }
    }
}