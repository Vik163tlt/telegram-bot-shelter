package com.example.tgbotshelter.exceptions;

public class ClientNotFoundException extends RuntimeException{
    public ClientNotFoundException() {
        super("Клиента с таким id нет!");
    }
}