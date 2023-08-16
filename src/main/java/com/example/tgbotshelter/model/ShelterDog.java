package com.example.tgbotshelter.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class ShelterDog {
    @Transient
    private transient List<Dog> dogs;
    @Transient
    private transient List<DogOwner> dogOwners;

    public ShelterDog() {
    }

    public String address() {
        try {
            return Files.readString(Paths.get("src/main/resources/draw/shelter_dog_address.txt"),
                    StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}