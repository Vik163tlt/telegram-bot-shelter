package com.example.tgbotshelter.utils;

import org.springframework.stereotype.Service;
import com.example.tgbotshelter.dto.CatDto;
import com.example.tgbotshelter.dto.CatOwnerDto;
import com.example.tgbotshelter.dto.DogDto;
import com.example.tgbotshelter.dto.DogOwnerDto;
import com.example.tgbotshelter.model.Cat;
import com.example.tgbotshelter.model.CatOwner;
import com.example.tgbotshelter.model.Dog;
import com.example.tgbotshelter.model.DogOwner;

@Service
public class MappingUtils {
    public CatDto mapToCatDto(Cat entity) {
        CatDto dto = new CatDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setBreed(entity.getBreed());
        dto.setDateOfBirth(entity.getDateOfBirth());
        dto.setSuit(entity.getSuit());
        dto.setGender(entity.getGender());
        return dto;
    }

    public Cat mapToCat(CatDto dto) {
        Cat entity = new Cat();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setBreed(dto.getBreed());
        entity.setDateOfBirth(dto.getDateOfBirth());
        entity.setSuit(dto.getSuit());
        entity.setGender(dto.getGender());
        entity.setCatOwner(null);
        return entity;
    }

    public DogDto mapToDogDto(Dog entity) {
        DogDto dto = new DogDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setBreed(entity.getBreed());
        dto.setDateOfBirth(entity.getDateOfBirth());
        dto.setSuit(entity.getSuit());
        dto.setGender(entity.getGender());
        return dto;
    }

    public Dog mapToDog(DogDto dto) {
        Dog entity = new Dog();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setBreed(dto.getBreed());
        entity.setDateOfBirth(dto.getDateOfBirth());
        entity.setSuit(dto.getSuit());
        entity.setGender(dto.getGender());
        entity.setDogOwner(null);
        return entity;
    }

    public DogOwnerDto mapToDogOwnerDto(DogOwner entity) {
        DogOwnerDto dto = new DogOwnerDto();
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setPhoneNumber(entity.getPhoneNumber());
        dto.setProbation(entity.getProbation());
        return dto;
    }
    public CatOwnerDto mapToCatOwnerDto(CatOwner entity) {
        CatOwnerDto dto = new CatOwnerDto();
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setPhoneNumber(entity.getPhoneNumber());
        dto.setProbation(entity.getProbation());
        return dto;
    }

    public DogOwner mapToDogOwner(DogOwnerDto dto) {
        DogOwner entity = new DogOwner();
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setPhoneNumber(dto.getPhoneNumber());
        entity.setProbation(dto.getProbation());
        return entity;
    }

    public CatOwner mapToCatOwner(CatOwnerDto dto) {
        CatOwner entity = new CatOwner();
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setPhoneNumber(dto.getPhoneNumber());
        entity.setProbation(dto.getProbation());
        return entity;
    }
}