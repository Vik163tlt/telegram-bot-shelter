package com.example.tgbotshelter.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;
@Getter
@Setter
public class DogOwnerDto {
    private Long id;
    private Long chatId;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private LocalDateTime probation;

    public DogOwnerDto(Long id, Long chatId, String firstName, String lastName, String phoneNumber,
                       LocalDateTime probation) {
        this.id = id;
        this.chatId = chatId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.probation = probation;
    }

    public DogOwnerDto(Long chatId, String firstName, String lastName, String phoneNumber,
                       LocalDateTime probation) {
        this.chatId = chatId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.probation = probation;
    }

    public DogOwnerDto() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        DogOwnerDto ownerDto = (DogOwnerDto) o;
        return Objects.equals(id, ownerDto.id) && Objects.equals(chatId, ownerDto.chatId) && Objects.equals(firstName, ownerDto.firstName) && Objects.equals(lastName, ownerDto.lastName) && Objects.equals(phoneNumber, ownerDto.phoneNumber) && Objects.equals(probation, ownerDto.probation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, chatId, firstName, lastName, phoneNumber, probation);
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