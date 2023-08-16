package com.example.tgbotshelter.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.example.tgbotshelter.dto.DogDto;
import com.example.tgbotshelter.exceptions.PetNotFoundException;
import com.example.tgbotshelter.model.Dog;
import com.example.tgbotshelter.model.DogOwner;
import com.example.tgbotshelter.repositories.ShelterDogRepository;
import com.example.tgbotshelter.services.DogService;
import com.example.tgbotshelter.utils.MappingUtils;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DogServiceTest {
    @InjectMocks
    private DogService dogService;
    @Mock
    private ShelterDogRepository shelterDogRepository;
    @Mock
    private MappingUtils mappingUtils;
    Dog dog = new Dog("Name", "breed", LocalDate.of(2000, 12, 31), "suit", "gender");
    DogDto dogDto = new DogDto("Name", "breed", LocalDate.of(2000, 12, 31), "suit", "gender");

    @Test
    @DisplayName("Проверка создания питомца")
    void createDog() {
        verify(shelterDogRepository, verificationData -> dogService.createDog(dog.getName(), dog.getBreed(),
                dog.getDateOfBirth(), dog.getSuit(), dog.getGender())).saveAndFlush(dog);
    }

    @Test
    @DisplayName("Проверка исключения при получении несуществующего питомца")
    void getPetNotFoundExceptionTest() {
        when(dogService.getDog(0L)).thenThrow(PetNotFoundException.class);
        assertThrows(PetNotFoundException.class,
                () -> when(dogService.getDog(0L)).thenThrow(PetNotFoundException.class));
    }

    @Test
    @DisplayName("Проверка получения всех питомцев")
    void getAllPets() {
        when(mappingUtils.mapToDogDto(dog)).thenReturn(dogDto);
        when(shelterDogRepository.findAll()).thenReturn(List.of(dog));
        assertEquals(List.of(dogDto), dogService.getAllDogs());
    }

    @Test
    @DisplayName("Проверка получения всех питомцев владельца")
    void getAllDogsByOwnerIdTest() {
        DogOwner dogOwner = new DogOwner();
        dogOwner.setId(0L);
        Dog test = new Dog(0L, "Name", "breed", LocalDate.of(2000, 12, 31), "suit", "gender", dogOwner);
        when(shelterDogRepository.findAll()).thenReturn(List.of(test));
        when(mappingUtils.mapToDogDto(test)).thenReturn(dogDto);
        assertEquals(List.of(dogDto), dogService.getAllDogs(0L));
    }

    @Test
    @DisplayName("Проверка получения питомца по id")
    void getPet() {
        when(mappingUtils.mapToDogDto(dog)).thenReturn(dogDto);
        when(shelterDogRepository.getReferenceById(0L)).thenReturn(dog);
        assertEquals(dogDto, dogService.getDog(0L));
    }

    @Test
    @DisplayName("Проверка удаления питомца")
    void deletePet() {
        verify(shelterDogRepository, verificationData -> dogService.deleteDog(0L)).deleteById(0L);
    }
}