package com.example.tgbotshelter.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
@Getter
@Setter
@Entity
@Table(name = "dog_owners")
public class DogOwner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "owner_id")
    private Long id;
    @Column(name = "chat_id")
    private Long chatId;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;
    @Column(name = "probation")
    private LocalDateTime probation;

    public DogOwner() {
    }

    public DogOwner(Long id, String firstName, String lastName, String phoneNumber, LocalDateTime probation) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.probation = probation;
    }

    public DogOwner(Long id, Long chatId, String firstName, String lastName, String phoneNumber,
                    LocalDateTime probation) {
        this.id = id;
        this.chatId = chatId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.probation = probation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DogOwner dogOwner = (DogOwner) o;
        return Objects.equals(id, dogOwner.id) && Objects.equals(chatId, dogOwner.chatId) && Objects.equals(firstName
                , dogOwner.firstName) && Objects.equals(lastName, dogOwner.lastName) && Objects.equals(phoneNumber,
                dogOwner.phoneNumber) && Objects.equals(probation, dogOwner.probation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, chatId, firstName, lastName, phoneNumber, probation);
    }

    @Override
    public String toString() {
        return "DogOwner{" +
                "id=" + id +
                ", chatId=" + chatId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", probation=" + probation +
                '}';
    }
}