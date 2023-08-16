package com.example.tgbotshelter.exceptions;

public class VolunteerNotFoundException extends RuntimeException{
    public VolunteerNotFoundException() {
        super("Волонтёра с таким id нет!");
    }
}