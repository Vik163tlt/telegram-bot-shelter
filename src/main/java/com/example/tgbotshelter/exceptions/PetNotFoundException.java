package com.example.tgbotshelter.exceptions;

public class PetNotFoundException extends RuntimeException {
    public PetNotFoundException() {
        super("Питомца с таким id нет!");
    }
}