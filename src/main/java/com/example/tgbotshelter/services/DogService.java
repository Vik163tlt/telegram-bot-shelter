package com.example.tgbotshelter.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.tgbotshelter.dto.DogDto;
import com.example.tgbotshelter.exceptions.PetNotFoundException;
import com.example.tgbotshelter.model.Dog;
import com.example.tgbotshelter.repositories.ShelterDogRepository;
import com.example.tgbotshelter.utils.MappingUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class DogService {
    private final ShelterDogRepository shelterDogRepository;
    private final MappingUtils mappingUtils;
    private List<Dog> dogs = new ArrayList<>();

    public DogService(ShelterDogRepository shelterDogRepository, MappingUtils mappingUtils) {
        this.shelterDogRepository = shelterDogRepository;
        this.mappingUtils = mappingUtils;
    }

    public void createDog(String name, String breed, LocalDate dateOfBirth, String suit, String gender) {
        DogDto dto = new DogDto(name, breed, dateOfBirth, suit, gender);
        Dog dog = mappingUtils.mapToDog(dto);
        dogs = shelterDogRepository.findAll();
        if (!dogs.contains(dog)) {
            shelterDogRepository.saveAndFlush(dog);
        }
    }

    public List<DogDto> getAllDogs() {
        dogs = shelterDogRepository.findAll();
        return dogs.stream().map(mappingUtils::mapToDogDto).collect(Collectors.toList());
    }

    public List<DogDto> getAllDogs(Long ownerId) {
        dogs = shelterDogRepository.findAll();
        for (int i = 0; i < dogs.size(); i++) {
            if (Objects.equals(Objects.requireNonNull(dogs.get(i).getDogOwner()).getId(), ownerId)) {
                return dogs.stream().map(mappingUtils::mapToDogDto).collect(Collectors.toList());
            }
        }
        //Этого никогда не может быть
        throw new PetNotFoundException();
    }

    @Transactional
    public DogDto getDog(Long id) {
        try {
            return mappingUtils.mapToDogDto(shelterDogRepository.getReferenceById(id));
        } catch (RuntimeException e) {
            throw new PetNotFoundException();
        }
    }

    public void deleteDog(Long id) {
        try {
            shelterDogRepository.deleteById(id);
        } catch (RuntimeException e) {
            throw new PetNotFoundException();
        }
    }
}