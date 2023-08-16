package com.example.tgbotshelter.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class ShelterCat {
    private transient List<Cat> cats;
    private transient List<CatOwner> catOwners;

    public ShelterCat() {
    }

    public String address() {
        try {
            return Files.readString(Paths.get("src/main/resources/draw/shelter_cat_address.txt"),
                    StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}