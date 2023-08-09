package com.example.tgbotshelter.dto;

import lombok.Getter;
import lombok.Setter;
import com.example.tgbotshelter.model.Pet;

import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
public class DogDto extends Pet {
    private Long id;
    private String name;
    private String breed;
    private LocalDate dateOfBirth;
    private String suit;
    private String gender;

    public DogDto(Long id, String name, String breed, LocalDate dateOfBirth, String suit, String gender) {
        this.id = id;
        this.name = name;
        this.breed = breed;
        this.dateOfBirth = dateOfBirth;
        this.suit = suit;
        this.gender = gender;
    }

    public DogDto(String name, String breed, LocalDate dateOfBirth, String suit, String gender) {
        this.name = name;
        this.breed = breed;
        this.dateOfBirth = dateOfBirth;
        this.suit = suit;
        this.gender = gender;
    }

    public DogDto() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DogDto dogDto = (DogDto) o;
        return Objects.equals(name, dogDto.name) && Objects.equals(breed, dogDto.breed) && Objects.equals(dateOfBirth, dogDto.dateOfBirth) && Objects.equals(suit, dogDto.suit) && Objects.equals(gender, dogDto.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, breed, dateOfBirth, suit, gender);
    }

    @Override
    public String toString() {
        return " " + gender +
                ", кличка: " + name +
                ", порода: " + breed +
                ", дата рождения: " + dateOfBirth +
                ", окрас: " + suit + " id " + id;
    }
}