package com.example.tgbotshelter.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.example.tgbotshelter.dto.CatDto;
import com.example.tgbotshelter.dto.CatOwnerDto;
import com.example.tgbotshelter.dto.DogDto;
import com.example.tgbotshelter.dto.DogOwnerDto;
import com.example.tgbotshelter.model.*;
import com.example.tgbotshelter.repositories.CatOwnerRepository;
import com.example.tgbotshelter.repositories.DogOwnerRepository;
import com.example.tgbotshelter.services.CatService;
import com.example.tgbotshelter.services.ClientService;
import com.example.tgbotshelter.services.DogService;
import com.example.tgbotshelter.services.OwnerService;
import com.example.tgbotshelter.utils.MappingUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OwnerServiceTest {
    @Mock
    private DogOwnerRepository dogOwnerRepository;
    @Mock
    private CatOwnerRepository catOwnerRepository;
    @Mock
    private DogService dogService;
    @Mock
    private CatService catService;
    @Mock
    private ClientService clientService;
    @InjectMocks
    private OwnerService ownerService;
    @Mock
    private MappingUtils mappingUtils;
    DogDto dogDto = new DogDto("Name", "breed", LocalDate.of(2000, 12, 31), "suit", "gender");
    Dog dog = new Dog("Name", "breed", LocalDate.of(2000, 12, 31), "suit", "gender");
    CatDto catDto = new CatDto("Name", "breed", LocalDate.of(2000, 12, 31), "suit", "gender");
    Cat cat = new Cat("Name", "breed", LocalDate.of(2000, 12, 31), "suit", "gender");
    DogOwner dogOwner = new DogOwner(0L, 0L, "firstName", "lastName", "7_xxx_xxx_xx_xx", LocalDateTime.now());
    DogOwnerDto dogOwnerDto = new DogOwnerDto(0L, "firstName", "lastName", "7_xxx_xxx_xx_xx",
            LocalDateTime.now());
    CatOwnerDto catOwnerDto = new CatOwnerDto(0L, 0L, "firstName", "lastName", "7_xxx_xxx_xx_xx",
            LocalDateTime.now());
    CatOwner catOwner = new CatOwner(0L, 0L, "firstName", "lastName", "7_xxx_xxx_xx_xx", LocalDateTime.now());
    Client client = new Client(0L, 0L, "FirstName", "LastName", "7_xxx_xxx_xx_xx");

    @Test
    @DisplayName("Проверка создания владельца")
    void createOwner() {
        when(clientService.getClientByChatId(0L)).thenReturn(client);
        when(dogService.getDog(0L)).thenReturn(dogDto);
        when(mappingUtils.mapToDog(dogDto)).thenReturn(dog);
        verify(dogOwnerRepository, verificationData -> ownerService.createDogOwner(0L, 0L)).saveAndFlush(dogOwner);
        when(catService.getCat(0L)).thenReturn(catDto);
        when(mappingUtils.mapToCat(catDto)).thenReturn(cat);
        verify(catOwnerRepository, verificationData -> ownerService.createCatOwner(0L, 0L)).saveAndFlush(catOwner);
    }

    @Test
    @DisplayName("Проверка получения владельца")
    void getOwner() {
        when(ownerService.getDogOwner(0L)).thenReturn(dogOwnerDto);
        assertEquals(dogOwnerDto, ownerService.getDogOwner(0L));
        when(ownerService.getCatOwner(0L)).thenReturn(catOwnerDto);
        assertEquals(catOwnerDto, ownerService.getCatOwner(0L));
    }

    @Test
    @DisplayName("Проверка обновления владельца")
    void updateOwner() {
        when(dogOwnerRepository.getReferenceById(0L)).thenReturn(dogOwner);
        verify(dogOwnerRepository, verificationData -> ownerService.updateDogOwner(dogOwner.getId(), dogOwner.getChatId(),
                dogOwner.getFirstName(), dogOwner.getLastName(), dogOwner.getPhoneNumber(),
                dogOwner.getProbation())).saveAndFlush(dogOwner);
        when(catOwnerRepository.getReferenceById(0L)).thenReturn(catOwner);
        verify(catOwnerRepository, verificationData -> ownerService.updateCatOwner(catOwner.getId(), catOwner.getChatId(),
                catOwner.getFirstName(), catOwner.getLastName(), catOwner.getPhoneNumber(),
                catOwner.getProbation())).saveAndFlush(catOwner);
    }

    @Test
    @DisplayName("Проверка удаления владельца")
    void deleteOwner() {
        verify(dogOwnerRepository, verificationData -> ownerService.deleteDogOwner(0L)).deleteById(0L);
        verify(catOwnerRepository, verificationData -> ownerService.deleteDogOwner(0L)).deleteById(0L);
    }
}