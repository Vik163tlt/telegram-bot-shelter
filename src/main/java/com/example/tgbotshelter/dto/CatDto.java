package com.example.tgbotshelter.dto;

import lombok.Getter;
import lombok.Setter;
import com.example.tgbotshelter.model.Pet;

import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
public class CatDto extends Pet {
    private Long id;
    private String name;
    private String breed;
    private LocalDate dateOfBirth;
    private String suit;
    private String gender;

    public CatDto(Long id, String name, String breed, LocalDate dateOfBirth, String suit, String gender) {
        this.id = id;
        this.name = name;
        this.breed = breed;
        this.dateOfBirth = dateOfBirth;
        this.suit = suit;
        this.gender = gender;
    }

    public CatDto(String name, String breed, LocalDate dateOfBirth, String suit, String gender) {
        this.name = name;
        this.breed = breed;
        this.dateOfBirth = dateOfBirth;
        this.suit = suit;
        this.gender = gender;
    }

    public CatDto() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CatDto dto = (CatDto) o;
        return Objects.equals(name, dto.name) && Objects.equals(breed, dto.breed) && Objects.equals(dateOfBirth, dto.dateOfBirth) && Objects.equals(suit, dto.suit) && Objects.equals(gender, dto.gender);
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