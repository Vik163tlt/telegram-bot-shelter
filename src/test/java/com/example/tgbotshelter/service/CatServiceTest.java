package com.example.tgbotshelter.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.example.tgbotshelter.dto.CatDto;
import com.example.tgbotshelter.exceptions.PetNotFoundException;
import com.example.tgbotshelter.model.Cat;
import com.example.tgbotshelter.model.CatOwner;
import com.example.tgbotshelter.repositories.ShelterCatRepository;
import com.example.tgbotshelter.services.CatService;
import com.example.tgbotshelter.utils.MappingUtils;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CatServiceTest {
    @InjectMocks
    private CatService catService;
    @Mock
    private ShelterCatRepository shelterCatRepository;
    @Mock
    private MappingUtils mappingUtils;
    Cat cat = new Cat("Name", "breed", LocalDate.of(2000, 12, 31), "suit", "gender");
    CatDto catDto = new CatDto("Name", "breed", LocalDate.of(2000, 12, 31), "suit", "gender");

    @Test
    @DisplayName("Проверка создания питомца")
    void createCat() {
        verify(shelterCatRepository, verificationData -> catService.createCat(cat.getName(), cat.getBreed(),
                cat.getDateOfBirth(), cat.getSuit(), cat.getGender())).saveAndFlush(cat);
    }

    @Test
    @DisplayName("Проверка исключения при получении несуществующего питомца")
    void getPetNotFoundExceptionTest() {
        when(catService.getCat(0L)).thenThrow(PetNotFoundException.class);
        assertThrows(PetNotFoundException.class,
                () -> when(catService.getCat(0L)).thenThrow(PetNotFoundException.class));
    }

    @Test
    @DisplayName("Проверка получения всех питомцев")
    void getAllCatsTest() {
        when(mappingUtils.mapToCatDto(cat)).thenReturn(catDto);
        when(shelterCatRepository.findAll()).thenReturn(List.of(cat));
        assertEquals(List.of(catDto), catService.getAllCats());
    }

    @Test
    @DisplayName("Проверка получения всех питомцев владельца")
    void getAllCatsByOwnerIdTest() {
        CatOwner catOwner = new CatOwner();
        catOwner.setId(0L);
        Cat test = new Cat(0L, "Name", "breed", LocalDate.of(2000, 12, 31), "suit", "gender", catOwner);
        when(shelterCatRepository.findAll()).thenReturn(List.of(test));
        when(mappingUtils.mapToCatDto(test)).thenReturn(catDto);
        assertEquals(List.of(catDto), catService.getAllCats(0L));
    }

    @Test
    @DisplayName("Проверка получения питомца по id")
    void getCat() {
        when(mappingUtils.mapToCatDto(cat)).thenReturn(catDto);
        when(shelterCatRepository.getReferenceById(0L)).thenReturn(cat);
        assertEquals(catDto, catService.getCat(0L));
    }

    @Test
    @DisplayName("Проверка удаления питомца")
    void deleteCat() {
        verify(shelterCatRepository, verificationData -> catService.deleteCat(0L)).deleteById(0L);
    }
}