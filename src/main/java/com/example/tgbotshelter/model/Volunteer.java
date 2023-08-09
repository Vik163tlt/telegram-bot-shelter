package com.example.tgbotshelter.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "volunteers")
public class Volunteer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;
    @Column(name = "chat_id")
    private Long chatId;
    @Transient
    private transient List<String> phoneNumbers = new ArrayList<>();

    public Volunteer() {
    }

    public Volunteer(Long id, Long chatId, String firstName, String lastName, String phoneNumber) {
        this.id = id;
        this.chatId = chatId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    public Long getChatId() {
        return chatId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Volunteer volunteer = (Volunteer) o;
        return Objects.equals(id, volunteer.id) && Objects.equals(firstName, volunteer.firstName) && Objects.equals(lastName, volunteer.lastName) && Objects.equals(phoneNumber, volunteer.phoneNumber) && Objects.equals(chatId, volunteer.chatId) && Objects.equals(phoneNumbers, volunteer.phoneNumbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, phoneNumber, chatId, phoneNumbers);
    }

    @Override
    public String toString() {
        return "Volunteer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", chatId=" + chatId +
                ", phoneNumbers=" + phoneNumbers +
                '}';
    }
}