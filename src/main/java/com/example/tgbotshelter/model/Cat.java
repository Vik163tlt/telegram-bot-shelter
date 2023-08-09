package com.example.tgbotshelter.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "cats")
public class Cat extends Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "breed", nullable = false)
    private String breed;
    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;
    @Column(name = "suit", nullable = false)
    private String suit;
    @Column(name = "gender", nullable = false)
    private String gender;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    @Nullable
    private CatOwner catOwner;

    public Cat(Long id, String name, String breed, LocalDate dateOfBirth, String suit, String gender,
               @org.jetbrains.annotations.Nullable CatOwner catOwner) {
        super(id);
        this.name = name;
        this.breed = breed;
        this.dateOfBirth = dateOfBirth;
        this.suit = suit;
        this.gender = gender;
        this.catOwner = catOwner;
    }

    public Cat() {
    }

    public Cat(String name, String breed, LocalDate dateOfBirth, String suit, String gender) {
        this.name = name;
        this.breed = breed;
        this.dateOfBirth = dateOfBirth;
        this.suit = suit;
        this.gender = gender;
    }

    public Cat(Long id, String name, String breed, LocalDate dateOfBirth, String suit, String gender) {
        super(id);
        this.name = name;
        this.breed = breed;
        this.dateOfBirth = dateOfBirth;
        this.suit = suit;
        this.gender = gender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cat cat = (Cat) o;
        return Objects.equals(id, cat.id) && Objects.equals(name, cat.name) && Objects.equals(breed, cat.breed) && Objects.equals(dateOfBirth, cat.dateOfBirth) && Objects.equals(suit, cat.suit) && Objects.equals(gender, cat.gender) && Objects.equals(catOwner, cat.catOwner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, breed, dateOfBirth, suit, gender, catOwner);
    }

    @Override
    public String toString() {
        return id + ", кличка: " + name +
                ", порода: " + breed +
                ", дата рождения: " + dateOfBirth +
                ", масть: " + suit +
                " " + gender;
    }
}