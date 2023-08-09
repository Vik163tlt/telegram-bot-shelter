package com.example.tgbotshelter.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
public class CatOwnerDto {
    private Long id;
    private Long chatId;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private LocalDateTime probation;

    public CatOwnerDto(Long id, Long chatId, String firstName, String lastName, String phoneNumber, LocalDateTime probation) {
        this.id = id;
        this.chatId = chatId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.probation = probation;
    }

    public CatOwnerDto(Long chatId, String firstName, String lastName, String phoneNumber) {
        this.chatId = chatId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    public CatOwnerDto() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CatOwnerDto that = (CatOwnerDto) o;
        return Objects.equals(id, that.id) && Objects.equals(chatId, that.chatId) && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(phoneNumber, that.phoneNumber) && Objects.equals(probation, that.probation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, chatId, firstName, lastName, phoneNumber, probation);
    }

    @Override
    public String toString() {
        return "OwnerDto{" +
                "id=" + id +
                ", chatId=" + chatId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", probation=" + probation;
    }
}