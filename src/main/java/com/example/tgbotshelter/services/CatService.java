package com.example.tgbotshelter.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.tgbotshelter.dto.CatDto;
import com.example.tgbotshelter.exceptions.PetNotFoundException;
import com.example.tgbotshelter.model.Cat;
import com.example.tgbotshelter.repositories.ShelterCatRepository;
import com.example.tgbotshelter.utils.MappingUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CatService {
    private final ShelterCatRepository shelterCatRepository;
    private final MappingUtils mappingUtils;
    private List<Cat> cats = new ArrayList<>();

    public CatService(ShelterCatRepository shelterCatRepository, MappingUtils mappingUtils) {
        this.shelterCatRepository = shelterCatRepository;
        this.mappingUtils = mappingUtils;
    }

    public void createCat(String name, String breed, LocalDate dateOfBirth, String suit, String gender) {
        CatDto dto = new CatDto(name, breed, dateOfBirth, suit, gender);
        Cat cat = mappingUtils.mapToCat(dto);
        cats = shelterCatRepository.findAll();
        if (!cats.contains(cat)) {
            shelterCatRepository.saveAndFlush(cat);
        }
    }

    public List<CatDto> getAllCats() {
        cats = shelterCatRepository.findAll();
        return cats.stream().map(mappingUtils::mapToCatDto).collect(Collectors.toList());
    }

    public List<CatDto> getAllCats(Long ownerId) {
        cats = shelterCatRepository.findAll();
        for (int i = 0; i < cats.size(); i++) {
            if (Objects.equals(Objects.requireNonNull(cats.get(i).getCatOwner()).getId(), ownerId)) {
                return cats.stream().map(mappingUtils::mapToCatDto).collect(Collectors.toList());
            }
        }
        //Этого никогда не может быть
        throw new PetNotFoundException();
    }

    @Transactional
    public CatDto getCat(Long id) {
        try {
            return mappingUtils.mapToCatDto(shelterCatRepository.getReferenceById(id));
        } catch (RuntimeException e) {
            throw new PetNotFoundException();
        }
    }

    public void deleteCat(Long id) {
        try {
            shelterCatRepository.deleteById(id);
        } catch (RuntimeException e) {
            throw new PetNotFoundException();
        }
    }
}